package com.baeldung.kotlin.kafka

import kotlinx.coroutines.runBlocking
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.TestInputTopic
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.TopologyTestDriver
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.JoinWindows
import org.apache.kafka.streams.test.TestRecord
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration


const val NUMBERS_TOPIC: String = "numbers"
const val FRANCHISE_TOPIC: String = "franchise"
const val MOVIE_NAME_TOPIC = "movie_names"
val FRANCHISES = listOf("Matorex", "Tremulator", "Nice Age", "Merry American Movie")

val EXPECTED_MOVIES = listOf("Matorex 4", "Tremulator 3", "Nice Age 2", "Merry American Movie 1")

@Testcontainers
class KafkaConnectionTest {
    companion object {
        @JvmStatic
        @Container
        val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
    }

    val KAFKA_BOOTSTRAP_SERVER = kafka.bootstrapServers

    val producerProps = mapOf(
        "bootstrap.servers" to KAFKA_BOOTSTRAP_SERVER,
        "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
        "value.serializer" to "org.apache.kafka.common.serialization.ByteArraySerializer",
        "security.protocol" to "PLAINTEXT"
    )
    val consumerProps = { group: String ->
        mapOf(
            "bootstrap.servers" to KAFKA_BOOTSTRAP_SERVER,
            "auto.offset.reset" to "earliest",
            "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
            "value.deserializer" to "org.apache.kafka.common.serialization.ByteArrayDeserializer",
            "group.id" to group,
            "security.protocol" to "PLAINTEXT"
        )
    }

    val streamConfig = mapOf<String, Any>(
        StreamsConfig.APPLICATION_ID_CONFIG to "streams-example",
        StreamsConfig.CLIENT_ID_CONFIG to "streams-example-client",
        StreamsConfig.COMMIT_INTERVAL_MS_CONFIG to 10L,
        StreamsConfig.POLL_MS_CONFIG to 10L,
        StreamsConfig.REPARTITION_PURGE_INTERVAL_MS_CONFIG to 300L,
        StreamsConfig.REPARTITION_PURGE_INTERVAL_MS_CONFIG to 500L,
        StreamsConfig.BOOTSTRAP_SERVERS_CONFIG to KAFKA_BOOTSTRAP_SERVER,
        StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG to Serdes.String().javaClass.name,
        StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG to Serdes.ByteArray().javaClass.name
    )

    @Test
    fun when_both_producer_and_consumer_are_connected_to_kafka_then_messages_flow() {
        KafkaProducer<String, ByteArray>(producerProps).use {
            it.send(ProducerRecord("test", "1", "Hello, world!".encodeToByteArray()))
        }
        KafkaConsumer<String, ByteArray>(consumerProps("baeldung-simple-test")).use {
            it.subscribe(listOf("test"))
            val message = repeatUntilSome {
                it.poll(400.milliseconds.toJavaDuration()).map { String(it.value()) }.firstOrNull()
            }
            assertNotNull(message)
        }
    }

    @Test
    fun when_send_is_wrapped_in_a_suspend_function_then_feedback_is_provided() = runBlocking {
        KafkaProducer<String, ByteArray>(producerProps).use {
            it.asyncSend(ProducerRecord("test-1", "1", "Hello, world!".encodeToByteArray()))
        }
        KafkaConsumer<String, ByteArray>(consumerProps("baeldung-simple-test")).use {
            it.subscribe(listOf("test-1"))
            val message = repeatUntilSome {
                it.poll(400.milliseconds.toJavaDuration()).map { String(it.value()) }.firstOrNull()
            }
            assertNotNull(message)
        }
        Unit
    }

    @Test
    fun when_two_topics_meet_then_they_produce_the_third_with_kafka_streams() {
        val topicConfig = setupTopics()
        KafkaStreams(getTopology(topicConfig), StreamsConfig(streamConfig)).use {
            it.cleanUp()
            it.start()

            populateData(topicConfig)

            val results: MutableList<String> = retrieveResultsFromOutputStream(topicConfig)
            assertEquals(EXPECTED_MOVIES, results)
        }
    }


    private fun retrieveResultsFromOutputStream(topicConfig: TopicConfig): MutableList<String> {
        val results: MutableList<String> = mutableListOf()
        consumer<Int, ByteArray>(consumerProps("baeldung-streams")).use {
            it.subscribe(listOf(topicConfig.outStream))
            while (results.size < 4) {
                readAvailable(it).let(results::addAll)
            }
        }
        return results
    }

    private fun readAvailable(consumer: KafkaConsumer<Int, ByteArray>) =
        repeatUntilSome {
            consumer.poll(100.milliseconds.toJavaDuration()).map { msg -> String(msg.value()) }
                .takeIf(List<String>::isNotEmpty)
        }

    private fun populateData(topicConfig: TopicConfig) =
        producer<String, ByteArray>(producerProps).use { kafkaProducer ->
            testData().forEachIndexed { i, (sequelNumber, franchiseName) ->
                kafkaProducer.send(ProducerRecord(topicConfig.inStream1, (i + 1).toString(), franchiseName))
                kafkaProducer.send(ProducerRecord(topicConfig.inStream2, (i + 1).toString(), sequelNumber))
            }
            kafkaProducer.flush()
        }

    private fun setupTopics(): TopicConfig {
        val topicConfig = TopicConfig(
            FRANCHISE_TOPIC, NUMBERS_TOPIC, MOVIE_NAME_TOPIC
        )
        deleteTopics(streamConfig, topicConfig)
        createTopics(streamConfig, topicConfig)
        return topicConfig
    }
}

tailrec fun <T> repeatUntilSome(block: () -> T?): T = block() ?: repeatUntilSome(block)

fun getTopology(topicConfig: TopicConfig): Topology = StreamsBuilder().apply {
    val (inStream1, inStream2, outStream) = topicConfig
    stream(inStream1, Consumed.with(Serdes.String(), Serdes.ByteArray())).join(
        stream(inStream2, Consumed.with(Serdes.String(), Serdes.ByteArray())),
        { name, num -> "${String(name)} ${String(num)}".encodeToByteArray() },
        JoinWindows.ofTimeDifferenceAndGrace(Duration.ofSeconds(3), Duration.ofSeconds(1))
    ).to(outStream)
}.build()

fun createTopics(allProps: Map<String, Any>, topicConfig: TopicConfig) = AdminClient.create(allProps).use {
    it.createTopics(
        listOf(
            NewTopic(topicConfig.inStream1, 1, 1),
            NewTopic(topicConfig.inStream2, 1, 1),
            NewTopic(topicConfig.outStream, 1, 1)
        )
    )
}

fun testData() = (1..4)
    .map { (5 - it).toString().encodeToByteArray() to FRANCHISES[it - 1].encodeToByteArray() }


fun deleteTopics(allProps: Map<String, Any>, topicConfig: TopicConfig) = AdminClient.create(allProps).use {
    it.deleteTopics(listOf(topicConfig.inStream1, topicConfig.inStream2, topicConfig.outStream))
}

inline fun <reified K, reified V> producer(props: Map<String, Any>): KafkaProducer<K, V> = KafkaProducer<K, V>(props)

inline fun <reified K, reified V> consumer(props: Map<String, Any>): KafkaConsumer<K, V> = KafkaConsumer<K, V>(props)


data class TopicConfig(
    val inStream1: String,
    val inStream2: String,
    val outStream: String
)