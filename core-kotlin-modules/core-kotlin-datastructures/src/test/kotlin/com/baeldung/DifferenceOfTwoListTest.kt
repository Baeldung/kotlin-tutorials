package com.baeldung
import org.junit.Test
import kotlin.test.assertContentEquals

data class User(val id: Int, val name: String)
class DifferenceOfTwoListTest {

    var list1 = listOf(User(1, "John"), User(2, "Fil"))
    var list2 = listOf(User(1, "John"), User(3, "Nappy"), User(2, "Fil"))

    @Test
    fun `get difference of two list brute force approach`(){
        var res = mutableListOf<User>()

        if(list1.size >= list2.size){
            for(user in list1){
                if (!list2.contains(user))
                    res.add(user)
            }
        }
        else
            for(user in list2){
                if (!list1.contains(user))
                    res.add(user)
            }

        assertContentEquals(listOf(User(id=3, name="Nappy")), res)
    }

    @Test
    fun `get difference of two lists with map and groupby`(){
        val sum = list1 + list2
        var res =  sum.groupBy { it.id }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        assertContentEquals(listOf(User(id=3, name="Nappy")), res)
    }

    @Test
    fun `get difference of two lists with filter method`(){
        var res = list2.filter { it.id !in list1.map { item -> item.id } }

        assertContentEquals(listOf(User(id=3, name="Nappy")), res)
    }

    @Test
    fun `get difference of two lists with hashmap method`(){
        val userOccurrences = HashMap<User, Int>()
        val sum = list1 + list2
        for (user in sum) {
            val numberOfOccurrences = userOccurrences[user]
            userOccurrences[user] = if(numberOfOccurrences == null) 1 else numberOfOccurrences + 1
        }
        var res = sum.filter { user -> userOccurrences[user] == 1 }
        assertContentEquals(listOf(User(id=3, name="Nappy")), res)
    }

    @Test
    fun `get difference of two lists with extension function`(){
        val res = list1 symmetricDifference list2
        assertContentEquals(listOf(User(id=3, name="Nappy")), res)

    }

    @Test
    fun `get difference of two list using ids`(){
        val list1Ids = list1.map { it.id }
        val list2Ids = list2.map { it.id }
        val commonIds = list1Ids.intersect(list2Ids)

        val sum = list1 + list2
        var res =  sum.filter { it.id !in commonIds }

        assertContentEquals(listOf(User(id=3, name="Nappy")), res)
    }

    infix fun <E> Collection<E>.symmetricDifference(other: Collection<E>): Set<E> {
        val left = this subtract other
        val right = other subtract this
        return left union right
    }
}