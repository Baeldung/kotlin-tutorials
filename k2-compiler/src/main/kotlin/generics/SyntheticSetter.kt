package generics

import generics.Box

fun execute(list: MutableList<*>, element: Any) {
  list.add(element)
}

fun explicitSetter() {
  val box = Box<String>()
  val tmpBox: Box<*> = box
  tmpBox.setValue (12) // Compile Error! Thats unsafe!
  val myValue : String? = box.value
}

fun syntheticSetter() {
  val box = Box<String>()
  val tmpBox : Box<*> = box
  tmpBox.value = 12 // That compiles!
  val foo : String? = box.value // And here we fail with ClassCastException
}

fun syntheticSetter_inVariance() {
  val box = Box<String>()
  val tmpBox : Box<in String> = box
  tmpBox.value = 12 // Wow, thats a trap again!
  val foo : String? = box.value // Blast! ClassCastException
}

