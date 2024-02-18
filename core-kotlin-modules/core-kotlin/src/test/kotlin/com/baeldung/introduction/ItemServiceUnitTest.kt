package com.baeldung.introduction

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class ItemServiceUnitTest {

    @Test
    fun givenItemId_whenGetForOptionalItem_shouldMakeActionOnNonNullValue() {
        //given
        val itemService = ItemService()

        //when
        val result = itemService.findItemNameForId()

        //then
        assertNotNull(result?.let { it.id })
        assertNotNull(result!!.id)
    }
}