package com.baeldung.spark.posts

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import spark.Spark

fun main() {
    val objectMapper = jacksonObjectMapper()
    objectMapper.registerModule(JavaTimeModule())
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    val repository = PostRepository()
    println(repository.create("1", "This is my first post"))
    println(repository.create("1", "And a second one"))
    println(repository.create("2", "Hello, World!"))

    Spark.before({ _, res -> res.type("application/json") })

    Spark.get("/posts", { req, _ ->
        val posterId = req.queryParams("posterId")

        val offset = req.queryParams("offset")?.toIntOrNull() ?: 0
        val count = req.queryParams("count")?.toIntOrNull() ?: 10

        if (posterId == null) {
            repository.getAll(offset, count)
        } else {
            repository.getForPoster(posterId, offset, count)
        }
    }, objectMapper::writeValueAsString)

    Spark.get("/posts/:id", { req, res ->
        val id = req.params("id")
        val post = repository.getById(id)

        if (post == null) {
            res.status(404)
        }

        post
    }, objectMapper::writeValueAsString)

    Spark.post("/posts", { req, res ->
        val body = objectMapper.readValue<CreatePostRequest>(req.bodyAsBytes())

        val post = repository.create(body.posterId, body.content)

        res.status(201)
        res.header("Location", "/posts/${post.id}")

        post
    }, objectMapper::writeValueAsString)

    Spark.delete("/posts/:id") { req, res ->
        val id = req.params("id")

        repository.deleteById(id)

        res.status(204)
    }

    Spark.patch("/posts/:id", { req, _ ->
        val id = req.params("id")

        val body = objectMapper.readValue<PatchPostRequest>(req.bodyAsBytes())

        repository.updateLikes(id, body.likes)
    }, objectMapper::writeValueAsString)

}

data class CreatePostRequest(val posterId: String, val content: String)

data class PatchPostRequest(val likes: Int)