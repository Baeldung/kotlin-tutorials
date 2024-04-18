package com.baeldung.access_private_members_from_extension_func

class Main {
  fun String?.containsIgnoreCase(target: String) : Boolean {
    if (this == null) {
      return false
    }

    val loadedClass = String::class
    val valueField = loadedClass.java.getDeclaredField("value")
    valueField?.isAccessible = true
    val actualValue = valueField?.get(this) as? ByteArray

    println(actualValue?.contentToString())

    return this.lowercase().contains(target.lowercase())
  }
}