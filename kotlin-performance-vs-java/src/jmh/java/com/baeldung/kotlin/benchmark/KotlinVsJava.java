package com.baeldung.kotlin.benchmark;

import com.baeldung.kotlin.collection.ops.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Currency;
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
        var pojo = new POJO(
                input.string1,
                input.fieldB,
                input.addressLine1,
                input.addressLine2,
                input.city,
                input.age,
                input.salary,
                input.currency,
                new InnerPOJO(input.childfieldA, input.childfieldB)
        );
        pojo.setFieldA(input.string2);
        blackhole.consume(pojo);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void changeFieldKotlin(Blackhole blackhole, InputString input) {
        var dataClass = new DataClass(
                input.string1,
                input.fieldB,
                input.addressLine1,
                input.addressLine2,
                input.city,
                input.age,
                input.salary,
                input.currency,
                new InnerDataClass(input.childfieldA, input.childfieldB)
        );
        blackhole.consume(DataClassKt.changeField(dataClass, input.string2));
    }

    @State(Scope.Benchmark)
    public static class InputString {
        public String string1 = "ABC";
        public String string2 = "XYZ";
        public String fieldB = "fieldB";
        public String addressLine1 = "Baker st., 221b";
        public String addressLine2 = "Marylebone";
        public String city = "London";
        public int age = (int) (31 + System.currentTimeMillis() % 17);
        public BigDecimal salary = new BigDecimal("30000.23");
        public Currency currency = Currency.getInstance("GBP");
        public String childfieldA = "a";
        public String childfieldB = "b";
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void changeFieldJava_changingOnly(Blackhole blackhole, InputJava input) {
        input.pojo.setFieldA(input.string2);
        blackhole.consume(input.pojo);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 5, warmups = 5)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void changeFieldKotlin_changingOnly(Blackhole blackhole, InputKotlin input) {
        blackhole.consume(DataClassKt.changeField(input.pojo, input.string2));
    }

    @State(Scope.Thread)
    public static class InputJava {
        public POJO pojo;

        @Setup(Level.Trial)
        public void setup() {
            String string1 = "ABC";
            String fieldB = "fieldB";
            String addressLine1 = "Baker st., 221b";
            String addressLine2 = "Marylebone";
            String city = "London";
            int age = (int) (31 + System.currentTimeMillis() % 17);
            BigDecimal salary = new BigDecimal("30000.23");
            Currency currency = Currency.getInstance("GBP");
            String childfieldA = "a";
            String childfieldB = "b";
            pojo = new POJO(string1, fieldB, addressLine1, addressLine2, city, age, salary, currency, new InnerPOJO(childfieldA, childfieldB));
        }

        public String string2 = "XYZ";
    }

    @State(Scope.Thread)
    public static class InputKotlin {
        public DataClass pojo;

        @Setup(Level.Trial)
        public void setup() {
            pojo = new DataClass(
                    "ABC",
                    "fieldB",
                    "Baker st., 221b",
                    "Marylebone",
                    "London",
                    (int) (31 + System.currentTimeMillis() % 17),
                    new BigDecimal("30000.23"),
                    Currency.getInstance("GBP"),
                    new InnerDataClass("a", "b")
            );
        }

        public String string2 = "XYZ";
    }
}
