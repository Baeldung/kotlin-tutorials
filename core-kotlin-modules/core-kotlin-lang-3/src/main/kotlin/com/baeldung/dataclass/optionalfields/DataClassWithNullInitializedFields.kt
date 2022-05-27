package com.baeldung.dataclass.optionalfields

class DataClassWithNullInitializedFields(
    val name: String? = null,
    val surname: String? = null,
    val age: Number? = null
)