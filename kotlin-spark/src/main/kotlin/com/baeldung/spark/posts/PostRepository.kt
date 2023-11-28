package com.baeldung.spark.posts

import java.time.Instant
import java.util.UUID

class PostRepository {
    private val data = mutableListOf<Post>()

    fun getAll(offset: Int, count: Int) : HitList<Post> {
        val page = data
            .sortedWith(compareByDescending { it.posted })
            .safeSubList(offset, offset + count)

        return HitList(entries = page, total = data.size)
    }

fun getForPoster(posterId: String, offset: Int, count: Int) : HitList<Post> {
    val total = data.count { post -> post.posterId == posterId }
    val page = data
        .sortedWith(compareByDescending { it.posted })
        .filter { it.posterId == posterId }
        .safeSubList(offset, offset + count)

    return HitList(entries = page, total = total)
}

    fun getById(id: String) : Post? {
        return data.find { it.id == id }
    }

    fun create(posterId: String, content: String) : Post {
        val newPost = Post(
            id = UUID.randomUUID().toString(),
            posterId = posterId,
            posted = Instant.now(),

            content = content,
            likes = 0
        )

        data.add(newPost)

        return newPost
    }

    fun deleteById(id: String) {
        data.removeIf { it.id == id }
    }

    fun updateLikes(id: String, likes: Int): Post {
        val existing = data.find { it.id == id }!!

        data.remove(existing)

        val newPost = existing.copy(likes = likes)
        data.add(newPost)

        return newPost
    }
}

private fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> =
    this.subList(fromIndex.coerceAtLeast(0), toIndex.coerceAtMost(this.size))