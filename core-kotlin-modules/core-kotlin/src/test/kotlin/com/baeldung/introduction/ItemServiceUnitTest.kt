package com.baeldung.introduction

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class ItemServiceUnitTest {

    @Test
    fun givenItemId_whenGetForOptionalItem_shouldMakeActionOnNonNullValue() {
        //given
        val id = "item_id"
        val itemService = ItemService()

        //when
        val result = itemService.findItemNameForId(id)

        //then
        assertNotNull(result?.let { it -> it.id })
        assertNotNull(result!!.id)
    }
}