inline fun <reified T> Iterable<*>.filterIsInstance() = filter { it is T }

fun main() {
    val set = setOf("1984", 2, 3, "Brave new world", 11)
    println(set.filterIsInstance<Int>())
}