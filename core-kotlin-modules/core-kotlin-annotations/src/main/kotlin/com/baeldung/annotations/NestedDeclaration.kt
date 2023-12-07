package com.baeldung.annotations

annotation class Parent(val type: Type) {
    annotation class Child1(val prop1: String)
    annotation class Child2(val prop2: Int)
    enum class Type { TYPE1, TYPE2 }
}

@Parent(Parent.Type.TYPE1)
@Parent.Child1("sample prop")
@Parent.Child2(prop2 = 1)
class ClassUsingNestedAnnotation {

}