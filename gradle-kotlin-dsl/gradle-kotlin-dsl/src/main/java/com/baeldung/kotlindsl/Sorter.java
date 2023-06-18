package com.baeldung.kotlindsl;

import com.baeldung.kotlindsl.Reporter.StockPrice;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Sorter {

    public List<StockPrice> sort(List<StockPrice> list) {
        return list.stream()
          .sorted(comparing(it -> it.timestamp))
          .collect(toList());
    }
}
