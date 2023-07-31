package com.baeldung.castList

class ListCasting {

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun castListToAnimalType(inputList: List<*>): List<Animal> {
            return inputList as List<Animal>
        }

        @Suppress("UNCHECKED_CAST")
        fun safeCastListToAnimalType(inputList: List<*>): List<Animal> {
            return inputList as? List<Animal> ?: listOf()
        }

        inline fun <reified T : Any> List<*>.containsOnly() = all { it is T }
    }
}