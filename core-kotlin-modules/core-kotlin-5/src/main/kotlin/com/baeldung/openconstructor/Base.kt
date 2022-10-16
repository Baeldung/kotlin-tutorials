package com.baeldung.openconstructor

abstract class Base {
  val fooResult by lazy { foo() }
  abstract fun foo(): Int
}

class BaseImplementationA(val value: Int) : Base() {
  override fun foo() = value
}

class BaseImplementationB(val value: Int) : Base() {
  override fun foo() = value
}
