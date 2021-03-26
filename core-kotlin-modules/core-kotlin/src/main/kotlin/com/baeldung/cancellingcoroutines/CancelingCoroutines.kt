import kotlinx.coroutines.*

@InternalCoroutinesApi
fun main(args: Array<String>) {
    testJob()
    cleanCode()
}

@InternalCoroutinesApi
fun testJob() {
    runBlocking {
        val deffered = async {
            //Perform the job
        }.await()

        val job = launch(Dispatchers.Default) {
            var i = 0;
            while (i < 5) {
//                if (!isActive) {
//                    throw getCancellationException()
//                } else {
//                    println(i)
//                    i += 1
//                }
                ensureActive()
                println(i)
                i += 1
            }
        }
        job.cancel()
    }
}

fun cleanCode() {
    runBlocking {
        val job = launch {
            try {
                work()
            } catch (e: CancellationException) {
                println(" Work cancelled!")
            } finally {
                println("Clean up!")
            }
        }
        delay(1000L)
        println("Cancel !")
        job.cancel()
        println("Done !")

    }
}

fun work() {
    var i = 0
    while (i < 5) {
        println(i)
        i += 1
    }

}