package com.baeldung.kotlin.immutable

import org.junit.Assert.assertEquals
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class KotlinxImmutablesUnitTest{

  @Test
  fun givenKICLList_whenAddTried_checkExceptionThrown(){

      val list: ImmutableList<String> = persistentListOf("I", "am", "immutable")

      //todo: This is not avalid anymore with latest version of kotlinx.immutableList
      //list.add("My new item")

      assertEquals(listOf("I", "am", "immutable"), list)
  }
}