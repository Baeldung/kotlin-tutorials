package com.baeldung.graphql.server.api

import com.baeldung.graphql.server.data.Conference
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object ConferencePublisher {

    // Replay here emits to new subscribers the last `n`
    private val mutableConferenceFlow: MutableSharedFlow<Conference> = MutableSharedFlow(replay = 1)

    // Readonly
    val conferenceFlow = mutableConferenceFlow.asSharedFlow()

    fun publishConference(conference: Conference) {
        mutableConferenceFlow.tryEmit(conference)
    }
}