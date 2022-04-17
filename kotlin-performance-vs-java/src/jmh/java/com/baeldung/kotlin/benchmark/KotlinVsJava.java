package com.baeldung.kotlin.benchmark;

import com.baeldung.kotlin.collection.ops.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KotlinVsJava {
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void inlinedLambdaKotlin(Blackhole blackhole) {
        blackhole.consume(DatabaseTransactionKt.transactedAction(System.currentTimeMillis()));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void lambdaJava(Blackhole blackhole) throws MalformedURLException {
        blackhole.consume(JavaDatabaseTransaction.transactedAction(System.currentTimeMillis()));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void collectionJava(Blackhole blackhole) {
        blackhole.consume(CollectionTransformations.transformList());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void collectionKotlin(Blackhole blackhole) {
        blackhole.consume(KotlinCollectionTransformationsKt.mapCollection());
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void smallCollectionJava(Blackhole blackhole) {
        blackhole.consume(CollectionTransformations.transformSmallList());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void smallCollectionKotlin(Blackhole blackhole) {
        blackhole.consume(KotlinCollectionTransformationsKt.mapSmallCollection());
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringCollectionJava(Blackhole blackhole, Input input) {
        blackhole.consume(CollectionTransformations.transformStringList(input.strings));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void stringCollectionKotlin(Blackhole blackhole, Input input) {
        blackhole.consume(KotlinCollectionTransformationsKt.transformStringList(input.strings));
    }


    @State(Scope.Benchmark)
    public static class Input {
        public List<String> strings = new ArrayList<>() {{
            add("Gt52z0BdN6");
            add("fmdOma7e1H");
            add("N0DSDXkC99");
            add("ZqznoKUZVU");
            add("8ZvGPZmEn1");
            add("0PRsGLMXLc");
            add("vpajT0trSu");
            add("yoplTkCZ2T");
            add("mFvIoiCaku");
            add("yViBNLJmAd");
        }};
    }


    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void varargsJava(Blackhole blackhole, Input input) {
        blackhole.consume(ArrayInVarargFunction.callConcatenate(input.strings.toArray(new String[]{})));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void varargsKotlin(Blackhole blackhole, Input input) {
        blackhole.consume(SpreadVarargFunctionKt.callVarargFunction(input.strings.toArray(new String[]{})));
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void changeFieldJava(Blackhole blackhole, InputString input) {
        var pojo = new POJO(input.string1);
        pojo.setFieldA(input.string2);
        blackhole.consume(pojo);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void changeFieldKotlin(Blackhole blackhole, InputString input) {
        var dataClass = new DataClass(input.string1);
        blackhole.consume(DataClassKt.changeField(dataClass, input.string2));
    }

    @State(Scope.Benchmark)
    public static class InputString {
        public String string1 = "ABC";
        public String string2 = "XYZ";
    }
}
