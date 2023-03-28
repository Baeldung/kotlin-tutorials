package com.baeldung.javaclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Person1 {
    private String name;
    private int age;
    private String gender;
    private int id;
}
