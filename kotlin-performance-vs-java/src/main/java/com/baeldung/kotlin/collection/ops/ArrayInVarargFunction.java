package com.baeldung.kotlin.collection.ops;

public class ArrayInVarargFunction {
public static String concatenate(String... pieces) {
    StringBuilder sb = new StringBuilder(pieces.length * 8);
    for(String p : pieces) {
        sb.append(p).append(",");
    }
    return sb.toString();
}

public static String callConcatenate(String[] pieces) {
    return concatenate(pieces);
}
}
