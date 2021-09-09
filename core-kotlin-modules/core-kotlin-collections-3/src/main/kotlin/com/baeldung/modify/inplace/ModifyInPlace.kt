package com.baeldung.modify.inplace

object ModifyInPlace {
    fun replaceEvenNumbersBy0Iterator(list: MutableList<Int>): MutableList<Int> {
        val iterator = list.listIterator()
        while(iterator.hasNext()) {
            val value = iterator.next()
            if (value % 2 == 0) {
                iterator.set(0)
            }
        }

        return list
    }

    fun replaceEvenNumbersBy0Direct(list: MutableList<Int>): MutableList<Int> {
        for (i in 0 until list.size) {
            val value = list[i]
            if (value % 2 == 0) {
                list[i] = 0
            }
        }

        return list
    }

    fun <T> MutableList<T>.mapInPlace(mutator: (T) -> (T)) {
        this.forEachIndexed { i, value ->
            val changedValue = mutator(value)

            if (value != changedValue) {
                this[i] = changedValue
            }
        }
    }

    fun <T> Array<T>.mapInPlace(mutator: (T) -> (T)) {
        this.forEachIndexed { i, value ->
            val changedValue = mutator(value)

            if (value != changedValue) {
                this[i] = changedValue
            }
        }
    }
}