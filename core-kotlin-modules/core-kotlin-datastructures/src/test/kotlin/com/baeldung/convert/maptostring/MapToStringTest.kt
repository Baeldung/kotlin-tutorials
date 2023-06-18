package com.baeldung.convert.maptostring

import com.google.gson.Gson
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class User(var firstName: String, var lastName: String)

class MapToStringTest {

    @Test
    fun `using joinToString function`(){
        val map = mapOf<Int, User>(
            1 to User("Flore", "P"),
            2 to User("Nappy", "Sean"),
            3 to User("Ndole", "Paul"),
        )

        val str = map.entries.joinToString(
            prefix = "[",
            separator = ", ",
            postfix = "]",
            limit = 2,
            truncated = "..."
        )

        assertEquals("1=User(firstName=Flore, lastName=P), 2=User(firstName=Nappy, lastName=Sean), 3=User(firstName=Ndole, lastName=Paul)",map.entries.joinToString())
        assertEquals("1=User(firstName=Flore, lastName=P)_2=User(firstName=Nappy, lastName=Sean)_3=User(firstName=Ndole, lastName=Paul)", map.entries.joinToString("_"))
        assertEquals("[1=User(firstName=Flore, lastName=P), 2=User(firstName=Nappy, lastName=Sean), ...]", str)
    }

    @Test
    fun `using toString function`(){
        val map = mapOf<Int, User>(
            1 to User("Flore", "P"),
            2 to User("Nappy", "Sean"),
            3 to User("Ndole", "Paul"),
        )

        assertEquals("{1=User(firstName=Flore, lastName=P), 2=User(firstName=Nappy, lastName=Sean), 3=User(firstName=Ndole, lastName=Paul)}", map.toString())
    }

    @Test
    fun `brute-force method`(){
        val map = mapOf<Int, User>(
            1 to User("Flore", "P"),
            2 to User("Nappy", "Sean"),
            3 to User("Ndole", "Paul"),
        )

        val sb = StringBuilder()
        map.forEach { entry ->
            sb.append("${entry.key}=${entry.value} ")
        }

        assertEquals("1=User(firstName=Flore, lastName=P) 2=User(firstName=Nappy, lastName=Sean) 3=User(firstName=Ndole, lastName=Paul) ", sb.toString())

    }

    @Test
    fun `converting to JSON first`(){
        val map = mapOf<Int, User>(
            1 to User("Flore", "P"),
            2 to User("Nappy", "Sean"),
            3 to User("Ndole", "Paul"),
        )

        val str = JSONObject(map).toString()
        val str1 = Gson().toJson(map).toString()
        assertEquals("{\"1\":{\"firstName\":\"Flore\",\"lastName\":\"P\"},\"2\":{\"firstName\":\"Nappy\",\"lastName\":\"Sean\"},\"3\":{\"firstName\":\"Ndole\",\"lastName\":\"Paul\"}}", str)

        assertEquals("{\"1\":{\"firstName\":\"Flore\",\"lastName\":\"P\"},\"2\":{\"firstName\":\"Nappy\",\"lastName\":\"Sean\"},\"3\":{\"firstName\":\"Ndole\",\"lastName\":\"Paul\"}}", str1)
    }
}