package com.baeldung.serializeArray

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


@Serializable
data class Post(val author: String, val title: String, val id: String)

@Serializable
data class MetaData(val location: String, val day: String)

@Serializable
data class PostWithMetaData(val author: String, val title: String, val id: String, val metaData: MetaData)

class ArraySerializationUnitTest {

    @Test
    fun `normal serialization of list of data class to JSON`() {
        val p1 = Post("Ethan", "Title 1", "1")
        val p2 = Post("Joel", "Title 2", "2")
        val p3 = Post("Jay", "Title 3", "3")
        val p4 = Post("Mat", "Title 4", "4")

        val posts = arrayOf(p1, p2, p3, p4)
        val jsonString = Json.encodeToString(posts)
        val res = "[{\"author\":\"Ethan\",\"title\":\"Title 1\",\"id\":\"1\"}," +
                "{\"author\":\"Joel\",\"title\":\"Title 2\",\"id\":\"2\"}," +
                "{\"author\":\"Jay\",\"title\":\"Title 3\",\"id\":\"3\"}," +
                "{\"author\":\"Mat\",\"title\":\"Title 4\",\"id\":\"4\"}]"
        assertEquals(res, jsonString)
    }

    @Test
    fun `desirialize JSON list into array of data class objects`(){
        val res = "[{\"author\":\"Ethan\",\"title\":\"Title 1\",\"id\":\"1\"}," +
                "{\"author\":\"Joel\",\"title\":\"Title 2\",\"id\":\"2\"}," +
                "{\"author\":\"Jay\",\"title\":\"Title 3\",\"id\":\"3\"}," +
                "{\"author\":\"Mat\",\"title\":\"Title 4\",\"id\":\"4\"}]"


        val posts = Json.decodeFromString<Array<Post>>(res)
        assertEquals(4,posts.size)
        assertEquals("Ethan", posts[0].author)
    }

    @Test
    fun `serialize nested data class objects into JSON`(){
        val m1 = MetaData("NY", "Monday")
        val m2 = MetaData("Dubai", "Friday")
        val m3 = MetaData("Croatia", "Thursday")
        val m4 = MetaData("Douala", "Wednesday")

        val p1 = PostWithMetaData("Ethan", "Title 1", "1", m1)
        val p2 = PostWithMetaData("Joel", "Title 2", "2", m2)
        val p3 = PostWithMetaData("Jay", "Title 3", "3", m3)
        val p4 = PostWithMetaData("Mat", "Title 4", "4", m4)

        val posts = arrayOf(p1, p2, p3, p4)
        val res = "[{\"author\":\"Ethan\",\"title\":\"Title 1\",\"id\":\"1\",\"metaData\":{\"location\":\"NY\",\"day\":\"Monday\"}}," +
                "{\"author\":\"Joel\",\"title\":\"Title 2\",\"id\":\"2\",\"metaData\":{\"location\":\"Dubai\",\"day\":\"Friday\"}}," +
                "{\"author\":\"Jay\",\"title\":\"Title 3\",\"id\":\"3\",\"metaData\":{\"location\":\"Croatia\",\"day\":\"Thursday\"}}," +
                "{\"author\":\"Mat\",\"title\":\"Title 4\",\"id\":\"4\",\"metaData\":{\"location\":\"Douala\",\"day\":\"Wednesday\"}}]"
        val jsonString = Json.encodeToString(posts)
        assertEquals(res, jsonString)
    }

    @Test
    fun `desirialize nested JSON into array of data class objects`(){
        val res = "[{\"author\":\"Ethan\",\"title\":\"Title 1\",\"id\":\"1\",\"metaData\":{\"location\":\"NY\",\"day\":\"Monday\"}}," +
                "{\"author\":\"Joel\",\"title\":\"Title 2\",\"id\":\"2\",\"metaData\":{\"location\":\"Dubai\",\"day\":\"Friday\"}}," +
                "{\"author\":\"Jay\",\"title\":\"Title 3\",\"id\":\"3\",\"metaData\":{\"location\":\"Croatia\",\"day\":\"Thursday\"}}," +
                "{\"author\":\"Mat\",\"title\":\"Title 4\",\"id\":\"4\",\"metaData\":{\"location\":\"Douala\",\"day\":\"Wednesday\"}}]"

        val posts = Json.decodeFromString<Array<PostWithMetaData>>(res)
        assertEquals(4,posts.size)
        assertEquals("Monday", posts[0].metaData.day)
        assertEquals("Friday", posts[1].metaData.day)
        assertEquals("Thursday", posts[2].metaData.day)
        assertEquals("Wednesday", posts[3].metaData.day)
    }
}