
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class Person(val name: String, val age: Int): Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return age.compareTo(other.age)
    }
}

fun insertionSortByAge(people: Array<Person>) {
    val n = people.size
    for (i in 1 until n) {
        val key = people[i]
        var j = i - 1

        while (j >= 0 && people[j] > key) {
            people[j + 1] = people[j]
            j--
        }
        people[j + 1] = key
    }
}

class SortingTests {
    @Test
    fun testInsertionSortByAge() {
        val people = arrayOf(
            Person("Alice", 30),
            Person("Bob", 25),
            Person("Charlie", 35),
            Person("David", 20)
        )
        insertionSortByAge(people)
        val expected = arrayOf(
            Person("David", 20),
            Person("Bob", 25),
            Person("Alice", 30),
            Person("Charlie", 35)
        )
        for (i in people.indices) {
            assertEquals(expected[i], people[i])
        }
    }
}
