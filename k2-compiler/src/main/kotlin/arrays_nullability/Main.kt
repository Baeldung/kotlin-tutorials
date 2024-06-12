package arrays_nullability

fun main() {
  val resultString : String? = StringUtils.fromCharArray(null) // Corret type should be String?
  val array : CharArray = StringUtils.toCharArray(null) // That compiles fine in K1
  println(array[0]) // NPE
}