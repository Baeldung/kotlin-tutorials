package com.baeldung.kotlin.kafka

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.TestInputTopic
import org.apache.kafka.streams.TopologyTestDriver
import org.apache.kafka.streams.test.TestRecord
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KafkaStreamLightTest {
    @Test
    fun when_test_utils_are_used_then_kafka_connection_is_not_needed_for_streams() {
        val testDriver = TopologyTestDriver(getTopology(topicConfig), testDriverConfig)

        val inStream1 = testDriver.createStandardTestTopic(topicConfig.inStream1)
        val inStream2 = testDriver.createStandardTestTopic(topicConfig.inStream2)
        testData().forEachIndexed { i, (sequelNumber, franchiseName) ->
            inStream1.pipeInput(TestRecord((i + 1).toString(), franchiseName))
            inStream2.pipeInput(TestRecord((i + 1).toString(), sequelNumber))
        }
        testDriver.createOutputTopic(
            topicConfig.outStream,
            Serdes.String().deserializer(),
            Serdes.ByteArray().deserializer()
        ).readValuesToList().map(::String)
            .let { assertEquals(EXPECTED_MOVIES, it) }
    }


    private fun TopologyTestDriver.createStandardTestTopic(topicName: String): TestInputTopic<String, ByteArray> =
        createInputTopic(topicName, Serdes.String().serializer(), Serdes.ByteArray().serializer())

    companion object {
        val topicConfig = TopicConfig(FRANCHISE_TOPIC, NUMBERS_TOPIC, MOVIE_NAME_TOPIC)
        val testDriverConfig = mapOf<String, String>(
            StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG to Serdes.String().javaClass.name,
            StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG to Serdes.ByteArray().javaClass.name
        ).toProperties()
    }
}