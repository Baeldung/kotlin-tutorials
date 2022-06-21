import kotlinx.coroutines.*

fun main() {
    ScopeAndJob()
}
fun ScopeAndJob() {
    val scope = CoroutineScope(Job())

    val job1 = scope.launch {
        try {
            delay(500)
            println("First Job")
        } catch (e: CancellationException) {
            e.printStackTrace()
        }
    }
    val job2 = scope.launch {
        delay(500)
        println("Second Job")
    }
    Thread.sleep(1000)
    //scope.cancel()
    job1.cancel()
    println("End of CoroutineScope")
}