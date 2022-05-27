package com.baeldung.dataclass.optionalfields

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DataClassWithFieldsTest {

    @Test
    fun whenAllFieldsProvided_thenCreatesObject() {
        val objectWithAllValuesProvided = DataClassWithMandatoryFields("John", "Deere", 82)
        assertThat(objectWithAllValuesProvided).isNotNull
    }

    @Test
    fun whenNotAllFieldsProvided_thenCreatesObject() {
        val objectWithNullInitializedFields = DataClassWithNullInitializedFields()
        assertThat(objectWithNullInitializedFields.name).isNull()

        val objectWithNameInitializedFields = DataClassWithNullInitializedFields(name = "John")
        assertThat(objectWithNameInitializedFields.name?.length).isEqualTo(4)

        val dataClassWithDefaultValues = DataClassWithDefaultValues()
        assertThat(dataClassWithDefaultValues).isNotNull

        val dataClassWithNameProvided = DataClassWithDefaultValues(name = "John")
        assertThat(dataClassWithNameProvided.name.length).isEqualTo(4)

        val dataClassWithSecondaryConstructors = DataClassWithSecondaryConstructors()
        assertThat(dataClassWithSecondaryConstructors).isNotNull

        val objectWithNameSet = DataClassWithSecondaryConstructors(name = "John" )
        assertThat(objectWithNameSet.surname).isEqualTo("Deere")
    }
}