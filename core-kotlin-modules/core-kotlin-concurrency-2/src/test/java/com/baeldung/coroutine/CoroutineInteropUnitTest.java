package com.baeldung.coroutine;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.future.FutureKt;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.baeldung.coroutine.JavaInteropKt.checkIn;
import static com.baeldung.coroutine.JavaInteropKt.checkInAsync;
import static com.baeldung.coroutine.JavaInteropKt.checkInClosed;

public class CoroutineInteropUnitTest {

    public static final String WELCOME = "Welcome";

    static class JavaContinuation<T> implements Continuation<T> {
        private final CompletableFuture<T> future;

        public JavaContinuation(CompletableFuture<T> future) {
            this.future = future;
        }

        @Override
        public void resumeWith(@NotNull Object o) {
            if (o instanceof Result.Failure)
                future.completeExceptionally(((Result.Failure) o).exception);
            else
                future.complete((T) o);
        }

        @NotNull
        @Override
        public CoroutineContext getContext() {
            return EmptyCoroutineContext.INSTANCE;
        }
    }

    @Test
    void givenCustomContinuation_whenCheckIn_getResult() throws ExecutionException, InterruptedException {
        CompletableFuture<String> suspendResult = new CompletableFuture<>();
        checkIn(new JavaContinuation<>(suspendResult));

        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

    @Test
    void givenCustomContinuation_whenCheckInClosed_getException() {
        CompletableFuture<String> suspendResult = new CompletableFuture<>();
        checkInClosed(new JavaContinuation<>(suspendResult));

        Assertions.assertThrows(Exception.class, suspendResult::get);
    }

    @Test
    void givenFutureHelper_whenCheckIn_getResult() throws InterruptedException, ExecutionException {
        CompletableFuture<String> suspendResult = FutureKt.future(CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), EmptyCoroutineContext.INSTANCE, CoroutineStart.DEFAULT, (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

    @Test
    void givenBuildersHelper_whenCheckIn_getResult() throws InterruptedException {
        String result = BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, result);
    }

    @Test
    void whenCheckInAsync_getResult() throws ExecutionException, InterruptedException {
        CompletableFuture<String> suspendResult = checkInAsync();
        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

}

