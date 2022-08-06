import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch { // 1
        repeat(5) { i ->
            println("Job execution is still in progress $i ...")
            delay(1000L)
        }
        println("Job execution is completed")
    }
    delay(1300L) // 2
    println("main: I'm going to cancel the coroutine!")
    job.cancel() // 3
    job.join() // 4
    println("main: Now I can quit.")
}
