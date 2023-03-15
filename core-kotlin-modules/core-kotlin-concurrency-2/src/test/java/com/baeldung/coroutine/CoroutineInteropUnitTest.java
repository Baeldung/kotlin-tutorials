package com.baeldung.coroutine;

import io.reactivex.rxjava3.core.Single;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.future.FutureKt;
import kotlinx.coroutines.reactor.MonoKt;
import kotlinx.coroutines.rx3.RxSingleKt;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.baeldung.coroutine.JavaInteropKt.checkIn;
import static com.baeldung.coroutine.JavaInteropKt.checkInAsync;
import static com.baeldung.coroutine.JavaInteropKt.checkInClosed;

public class CoroutineInteropUnitTest {

    public static final String WELCOME = "Welcome";

    static class CustomContinuation<T> implements Continuation<T> {
        private final CompletableFuture<T> future;

        public CustomContinuation(CompletableFuture<T> future) {
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
        checkIn(new CustomContinuation<>(suspendResult));

        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

    @Test
    void givenCustomContinuation_whenCheckInClosed_getException() {
        CompletableFuture<String> suspendResult = new CompletableFuture<>();
        checkInClosed(new CustomContinuation<>(suspendResult));

        Assertions.assertThrows(Exception.class, suspendResult::get);
    }

    @Test
    void givenFutureHelper_whenCheckIn_getResult() throws InterruptedException, ExecutionException {
        CompletableFuture<String> suspendResult = FutureKt.future(
          CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE),
          EmptyCoroutineContext.INSTANCE,
          CoroutineStart.DEFAULT,
          (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

    @Test
    void givenBlockingHelper_whenCheckIn_getResult() throws InterruptedException {
        String result = BuildersKt.runBlocking(
          EmptyCoroutineContext.INSTANCE,
          (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, result);
    }

    @Test
    void givenRxHelper_whenCheckIn_getResult() {
        Single<String> suspendResult = RxSingleKt.rxSingle(
          EmptyCoroutineContext.INSTANCE,
          (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, suspendResult.blockingGet());
    }

    @Test
    void givenReactorHelper_whenCheckIn_getResult() {
        Mono<String> suspendResult = MonoKt.mono(
          EmptyCoroutineContext.INSTANCE,
          (scope, continuation) -> checkIn(continuation));
        Assertions.assertEquals(WELCOME, suspendResult.block());
    }

    @Test
    void whenCheckInAsync_getResult() throws ExecutionException, InterruptedException {
        CompletableFuture<String> suspendResult = checkInAsync();
        Assertions.assertEquals(WELCOME, suspendResult.get());
    }

}

