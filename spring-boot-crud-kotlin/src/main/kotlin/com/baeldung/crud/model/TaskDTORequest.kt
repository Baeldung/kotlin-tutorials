package com.baeldung.crud.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskDTORequest(
    @JsonProperty("id")
    var name: String,
    @JsonProperty("name")
    var description: String,
    @JsonProperty("done")
    var done: Boolean
)