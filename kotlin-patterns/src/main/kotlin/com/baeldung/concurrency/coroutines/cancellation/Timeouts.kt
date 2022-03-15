import kotlinx.coroutines.*

fun main() = runBlocking {
    withTimeout(2000L) {
        for (i in 1..45) {
            yield()
            println("Fibonacci of $i is ${fibonacci(i)}")
        }
    }
}

fun fibonacci(num: Int): Int {
    return if (num == 0 || num == 1) num
    else fibonacci(num - 1) + fibonacci(num - 2)
}
