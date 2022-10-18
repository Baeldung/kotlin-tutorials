package com.baeldung.typeOfVariable

import com.baeldung.typeOfVariable.VariableType.*
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

open class Person(val name: String, val age: Int)

interface Ranking

class Player(name: String, age: Int, val numberOfWins: Int) : Person(name, age), Ranking

enum class VariableType {
    INT, STRING, PLAYER, UNKNOWN
}

fun Any.getType(): VariableType {
    return when (this) {
        is Int -> INT
        is String -> STRING
        is Player -> PLAYER
        else -> UNKNOWN
    }
}

class TypeOfVariableUnitTest {

    private val myInt: Any = 42
    private val myString: Any = "I am a string"
    private val myPlayer: Any = Player("Jackson", 42, 100)

    @Test
    fun `given a variable when check type with the is operator then get expected result`() {
        assertTrue { INT == myInt.getType() }
        assertTrue { STRING == myString.getType() }
        assertTrue { PLAYER == myPlayer.getType() }

        assertTrue { myPlayer is Person }
        assertTrue { myPlayer is Ranking }
    }

    @Test
    fun `given a variable when check get by KClass then get expected result`() {
        assertTrue { "String" == myString::class.simpleName }
        assertTrue { "Int" == myInt::class.simpleName }
        assertTrue { "Player" == myPlayer::class.simpleName }

        assertTrue { "kotlin.String" == myString::class.qualifiedName }
        assertTrue { "kotlin.Int" == myInt::class.qualifiedName }
        assertTrue { "com.baeldung.typeOfVariable.Player" == myPlayer::class.qualifiedName }
    }

    @Test
    fun `given a variable when get type by java Class then get expected result`() {
        assertTrue { "java.lang.String" == myString::class.java.typeName }
        assertTrue { "java.lang.Integer" == myInt::class.java.typeName }
        assertTrue { "com.baeldung.typeOfVariable.Player" == myPlayer::class.java.typeName }
    }

}
