package com.baeldung.castList

import com.baeldung.castList.ListCasting.Companion.containsOnly
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ListCastingTest {

    @Test
    fun `when casting exception is thrown`() {
        val listOfObjects = listOf(1, 2, Animal("Dog"))
        val animalList: List<Animal> = ListCasting.castListToAnimalType(listOfObjects)
        assertThat(animalList).hasSize(3)
        assertThrows<ClassCastException> {
            animalList[0].name
        }
    }

    @Test
    fun `when safe casting exception is thrown`() {
        val listOfObjects = listOf(1, 2, Animal("Dog"))
        val animalList = ListCasting.safeCastListToAnimalType(listOfObjects)
        assertThat(animalList).hasSize(3)
        assertThrows<ClassCastException> {
            animalList[0].name
        }
    }

    @Test
    fun `when checking list elements then it works`() {
        val listOfObjects = listOf(1, 2, Animal("Dog"))
        val animalList = listOfObjects.filterIsInstance<Animal>()
        assertThat(animalList).hasSize(1)
        assertThat(animalList[0].name).isEqualTo("Dog")
    }

    @Test
    fun `when checking list contains only Animals then it return false`() {
        val listOfObjects = listOf(1, 2, Animal("Dog"))
        val containsOnlyAnimals = listOfObjects.containsOnly<Animal>()
        assertThat(containsOnlyAnimals).isFalse()
    }
}