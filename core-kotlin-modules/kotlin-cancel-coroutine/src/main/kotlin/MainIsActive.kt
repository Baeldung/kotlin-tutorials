import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Job execution is still in progress ${i++} ...")
                nextPrintTime += 1000L
            }
        }
    }
    delay(1300L)
    println("main: I'm going to cancel the coroutine!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}
