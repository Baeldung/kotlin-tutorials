package com.baeldung.logfilter

import org.springframework.http.HttpHeaders
import java.util.stream.Collectors

fun HttpHeaders.asString(): String {
    return entries
        .stream()
        .map { entry: Map.Entry<String, List<String?>?> ->
            " ${entry.key}: [" + java.lang.String.join(
                ";",
                entry.value
            ) + "]"
        }
        .collect(Collectors.joining("\n"))
}