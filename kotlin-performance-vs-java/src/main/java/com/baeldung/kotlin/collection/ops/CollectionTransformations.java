package com.baeldung.kotlin.collection.ops;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionTransformations {

    public static List<String> transformList() {
        return IntStream.range(1, 100_000)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> transformSmallList() {
        return IntStream.range(1, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> transformStringList(List<String> strings) {
        return strings.stream().map(s -> s + System.currentTimeMillis()).collect(Collectors.toList());
    }
}
