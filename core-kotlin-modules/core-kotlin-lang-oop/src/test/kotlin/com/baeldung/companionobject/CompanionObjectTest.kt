package com.baeldung.companionobject

import com.baeldung.enums.CardType
import org.junit.Assert.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompanionObjectTest{
    @Test
    fun givenFirstRelatedClass_whenFactoryIsFirstRelatedClass_thenReturnFirstRelatedClassType() {
        val firstFactory: CompanionObject.AbstractFactory.FactoryCreator = CompanionObject.AbstractFactory.FirstRelatedClass.Factory
        Assertions.assertTrue(firstFactory.produce() is CompanionObject.AbstractFactory.FirstRelatedClass)
    }
}