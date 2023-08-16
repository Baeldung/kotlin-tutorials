package com.baeldung.fuel

import com.github.kittinunf.fuel.core.HeaderValues
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting

sealed class PostRoutingAPI : FuelRouting {

    override val basePath = "https://jsonplaceholder.typicode.com"

    class Post(val id: String, override val body: String?) : PostRoutingAPI()

    class Comment(val postId: String, override val body: String?) : PostRoutingAPI()

    override val method: Method
        get() {
            return when (this) {
                is Post -> Method.GET
                is Comment -> Method.GET
            }
        }

    override val path: String
        get() {
            return when (this) {
                is Post -> "/posts"
                is Comment -> "/comments"
            }
        }

    override val params: List<Pair<String, Any?>>?
        get() {
            return when (this) {
                is Post -> listOf("id" to this.id)
                is Comment -> listOf("postId" to this.postId)
            }
        }

    override val headers: Map<String, HeaderValues>?
        get() = null

    override val bytes: ByteArray?
        get() = null
}