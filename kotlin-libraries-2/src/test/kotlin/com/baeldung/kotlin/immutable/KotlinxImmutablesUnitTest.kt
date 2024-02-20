package com.baeldung.kotlin.immutable

import org.junit.Assert.assertEquals
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import org.junit.Test

class KotlinxImmutablesUnitTest{

  @Test
  fun givenKICLList_whenAddTried_checkExceptionThrown(){

      val list: ImmutableList<String> = immutableListOf("I", "am", "immutable")

      list.add("My new item")

      assertEquals(listOf("I", "am", "immutable"), list)
  }
}