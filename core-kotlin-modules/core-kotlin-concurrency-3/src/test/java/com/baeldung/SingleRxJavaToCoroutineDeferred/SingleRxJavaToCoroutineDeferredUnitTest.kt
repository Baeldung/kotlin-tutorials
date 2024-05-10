package com.baeldung.SingleRxJavaToCoroutineDeferred

import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SingleRxJavaToCoroutineDeferredUnitTest {
    fun <T> Single<T>.toDeferred(): Deferred<Unit> = CoroutineScope(Dispatchers.IO).async {
        this@toDeferred
    }
}