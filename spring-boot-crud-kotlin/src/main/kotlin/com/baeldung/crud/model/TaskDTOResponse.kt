package com.baeldung.crud.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskDTOResponse(
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("name")
    var name: String,
    @JsonProperty("description")
    var description: String,
    @JsonProperty("done")
    var done: Boolean
)