package com.baeldung.graphql.server.api

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Subscription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ConferenceSubscription : Subscription {

    @GraphQLDescription("Emits single, newly created conferences")
    fun conferenceId(): Flow<Int> = ConferencePublisher.conferenceFlow.map { it.id!! }

}