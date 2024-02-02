package com.baeldung.returnmultiple

import java.net.InetAddress
import java.security.KeyPair
import java.security.KeyPairGenerator

fun twoPair(): Pair<String, Int> = "Ali" to 33
fun threeValues(): Triple<String, Int, String> = Triple("Ali", 33, "Neka")
fun fiveValues() = arrayOf("Berlin", "Munich", "Amsterdam", "Madrid", "Vienna")

data class Pod(val name: String, val ip: InetAddress, val assignedNode: String)
fun getUniquePod() = Pod("postgres", InetAddress.getLocalHost(), "Node 1")

operator fun KeyPair.component1(): ByteArray = public.encoded
operator fun KeyPair.component2(): ByteArray = private.encoded

fun getRsaKeyPair(): KeyPair = KeyPairGenerator.getInstance("RSA").genKeyPair()

fun main() {
    val (name, age) = twoPair()
    println("$name is $age years old")

    val (_, _, _) = threeValues()
    val (_, _, _, _, _) = fiveValues()
    val (_, _, _) = getUniquePod()
    val (_, _) = getRsaKeyPair()
}