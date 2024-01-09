package com.baeldung.dataobjects

class DataObjectsExample {
    data class MyDataClass(val name: String)
    object MyRegularObject
    data object MyDataObject

    // print
    fun printObjects() {
        println(MyDataClass("Jon")) // prints: MyDataClass(name=Jon)
        println(MyRegularObject.toString()) // prints: DataObjects$MyRegularObject@1b6d3586
        println(MyDataObject) // prints: MyDataObject
    }
}