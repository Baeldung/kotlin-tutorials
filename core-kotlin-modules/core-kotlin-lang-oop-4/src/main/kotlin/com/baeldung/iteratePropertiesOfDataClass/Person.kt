package com.baeldung.iteratePropertiesOfDataClass

import oliviazoe0.processor.AutoUnpack

@AutoUnpack
data class Person(val name: String, val age: Int)