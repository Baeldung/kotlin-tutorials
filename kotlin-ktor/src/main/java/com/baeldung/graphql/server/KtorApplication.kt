package com.baeldung.graphql.server

import com.baeldung.graphql.server.api.AttendeeMutation
import com.baeldung.graphql.server.api.ConferenceMutation
import com.baeldung.graphql.server.api.ConferenceQuery
import com.baeldung.graphql.server.api.ConferenceSubscription
import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.hooks.FlowSubscriptionSchemaGeneratorHooks
import com.expediagroup.graphql.generator.toSchema
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphQLSubscriptionsRoute
import graphql.schema.idl.SchemaPrinter
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import java.time.Duration

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::graphQLModule).start(wait = true)
}

fun Application.graphQLModule() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(1)
        contentConverter = JacksonWebsocketContentConverter()
    }
    install(GraphQL) {
        schema {
            packages = listOf("com.baeldung.graphql.server")
            queries = listOf(ConferenceQuery())
            mutations = listOf(ConferenceMutation(), AttendeeMutation())
            subscriptions = listOf(ConferenceSubscription())
        }
    }
    install(Routing) {
        graphQLPostRoute()
        graphQLSubscriptionsRoute()
    }
}