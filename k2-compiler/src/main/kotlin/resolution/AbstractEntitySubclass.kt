package resolution

class AbstractEntitySubclass(val type: String) : AbstractEntity() {

  val status: String
    get() = "CONCRETE_STATUS"
}

fun main() {
  val sublcass = AbstractEntitySubclass("CONCRETE_TYPE")
  println(sublcass.type)
  println(sublcass.status)
}