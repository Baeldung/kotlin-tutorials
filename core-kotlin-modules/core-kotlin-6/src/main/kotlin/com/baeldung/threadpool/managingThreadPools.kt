package com.baeldung.threadpool

import java.io.Closeable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

interface TaskManager : Closeable {
    val workerPool: ExecutorService

    fun printMessage(producer: () -> String) {
        workerPool.submit { "${Thread.currentThread().name}: " + producer() }
    }

    override fun close() {
        workerPool.awaitTermination(1, TimeUnit.SECONDS)
    }
}

class SimpleTaskManager : TaskManager {
    override val workerPool: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
}

class SingleThreadTaskManager : TaskManager {
    override val workerPool: ExecutorService = Executors.newSingleThreadExecutor()

    fun work() {
        // Do some in-thread CPU-intensive work
        println("I am working hard")
        workerPool.submit {
            Thread.sleep(100) // Imitate slow IO
            println("I am reporting on the progress")
        }
        println("Meanwhile I continue to work")
    }
}

class CachedTaskManager : TaskManager {
    override val workerPool: ExecutorService = Executors.newCachedThreadPool()
}

class LimitedCachedTaskmanager : TaskManager {
    private val corePoolSize = 4
    private val maximumPoolSize = corePoolSize * 4
    private val keepAliveTime = 100L
    private val workQueue = SynchronousQueue<Runnable>()
    override val workerPool: ExecutorService = ThreadPoolExecutor(
      corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue
    )
}

class WorkStealingTaskManager : TaskManager {
    override val workerPool = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors())
}

class CustomTaskManager : TaskManager {
    private val corePoolSize = 4
    private val maximumPoolSize = corePoolSize * 4
    private val keepAliveTime = 100L
    private val workQueue = SynchronousQueue<Runnable>()
    private val threadFactory = Executors.defaultThreadFactory()
    private val handler = ThreadPoolExecutor.DiscardPolicy()
    override val workerPool: ExecutorService = ThreadPoolExecutor(
      corePoolSize,
      maximumPoolSize,
      keepAliveTime,
      TimeUnit.SECONDS,
      workQueue,
      threadFactory,
      handler
    )
}
