package com.baeldung.spring.integration.dsl

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.core.MessageSource
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.integrationFlow
import org.springframework.integration.file.FileReadingMessageSource
import org.springframework.integration.file.FileWritingMessageHandler
import org.springframework.integration.file.support.FileExistsMode
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import java.io.File

@EnableIntegration
@Configuration
class IntegrationFlowConfig {

    val inputDir = "source"
    val outputDir = "target"

    @Bean
    fun sourceDirectory(): MessageSource<File> {
        val messageSource = FileReadingMessageSource()
        messageSource.setDirectory(File(inputDir))
        return messageSource
    }

    @Bean
    fun targetDirectory(): MessageHandler {
        val handler = FileWritingMessageHandler(File(outputDir))
        handler.setFileExistsMode(FileExistsMode.REPLACE)
        handler.setExpectReply(false)
        return handler
    }

    @Bean
    fun kotlinFileMover(): IntegrationFlow = integrationFlow(sourceDirectory(), { poller { it.fixedDelay(10000) } }) {
        filter { message: File -> message.name.endsWith(".jpg") }
        handle("targetDirectory")
    }


    @Bean
    fun simpleFlow(): IntegrationFlow {
        return integrationFlow {
            filter<String> { it === "Spring Integration with Kotlin | Baeldung" }
            wireTap {
                handle { message -> println(message.payload) }
            }
            transform<String> { it.uppercase() }
        }
    }

    @Bean
    fun baeldung(): MessageChannel = MessageChannels.queue().get()

    @Bean
    fun mixedFlow(): IntegrationFlow {
        return integrationFlow(simpleFlow()) {
            transform<String> { it.lowercase() }
        }
    }

}