package com.baeldung.access_private_members_from_extension_func

class Main {
  fun String?.containsIgnoreCase(target: String) : Boolean {
    if (this == null) {
      return false
    }

//    val loadedClass = Class.forName("java.lang.String")
//    val valueField = loadedClass.getDeclaredField("value")
//    valueField.isAccessible = true
//    val actualValue = valueField.get(this)

//    println((actualValue as ByteArray).contentToString())

    return this.lowercase().contains(target.lowercase())
  }
}