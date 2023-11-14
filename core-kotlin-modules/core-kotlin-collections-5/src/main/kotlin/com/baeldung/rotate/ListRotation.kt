package com.baeldung.rotate

internal object ListRotation {
    internal fun <E> List<E>.rotateLeftUsingSubList(distance: Int): List<E> {
        return this.subList(distance, this.size) + this.subList(0, distance)
    }

    internal fun <E> List<E>.rotateRightUsingSubList(distance: Int): List<E> {
        return this.subList(this.size - distance, this.size) + this.subList(0, this.size - distance)
    }

    internal fun <T> Iterable<T>.rotateLeftUsingDrop(n: Int) = this.drop(n) + this.take(n)
    internal fun <T> List<T>.rotateRightUsingDrop(n: Int) = this.takeLast(n) + this.dropLast(n)
}