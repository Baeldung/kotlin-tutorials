import kotlinx.coroutines.*

fun main() = runBlocking {
    val job: Job = GlobalScope.launch {
        for (i in 1..45)
            println("Fibonacci of $i is ${fibonacci(i)}")
    }

    delay(2000)
    job.cancel()
    job.join()
}
private fun fibonacci(num: Int): Int {
    return if (num == 0 || num == 1) num
    else fibonacci(num - 1) + fibonacci(num - 2)
}

