data class Person(val name: String, val age: Int)
fun insertionSortByAge(people: Array<Person>) {
    val n = people.size
    for (i in 1 until n) {
        val key = people[i]
        var j = i - 1

        while (j >= 0 && people[j].age > key.age) {
            people[j + 1] = people[j]
            j--
        }
        people[j + 1] = key
    }
}

fun insertionSortWithArrays() {
    val people = arrayOf(
        Person("Alice", 30),
        Person("Bob", 25),
        Person("Charlie", 35),
        Person("David", 28)
    )
    insertionSortByAge(people)
    for (person in people) {
        println("${person.name}, ${person.age}")
    }
}
