package com.baeldung.unusedParamter

fun paramInFunction(num: Int) {
    println("We only use the Int parameter: $num")
}


fun paramInFunction2(num2: Int, @Suppress("UNUSED_PARAMETER") magic2: String) {
    println("We only use the Int parameter: $num2")
}

@Suppress("UNUSED_PARAMETER")
fun paramInFunction3(num3: Int, magic3: String) {
    println("We only use the Int parameter: $num3")
}


fun paramInLambda() {
    listOf("Kotlin", "Java", "Python").forEachIndexed { idx, _ -> println("We only print the of index the element: $idx") }
}

fun paramInLambda2() {
    listOf("Kotlin", "Java", "Python").forEachIndexed { idx, _ -> println("We only print the index of the element: $idx") }
}