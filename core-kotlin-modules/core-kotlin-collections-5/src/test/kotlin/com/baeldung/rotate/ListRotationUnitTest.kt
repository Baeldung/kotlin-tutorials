package com.baeldung.rotate

import com.baeldung.rotate.ListRotation.rotateLeftUsingDrop
import com.baeldung.rotate.ListRotation.rotateLeftUsingSubList
import com.baeldung.rotate.ListRotation.rotateRightUsingDrop
import com.baeldung.rotate.ListRotation.rotateRightUsingSubList
import java.util.Collections
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ListRotationUnitTest {

    @Test
    internal fun `rotates a list left using rotate`() {
        // given
        val list = listOf(1, 2, 3, 4, 5)

        // when
        Collections.rotate(list, -2)

        // then
        assertThat(list).isEqualTo(listOf(3, 4, 5, 1, 2))
    }

    @Test
    internal fun `rotates a list right using rotate`() {
        // given
        val list = listOf(1, 2, 3, 4, 5)

        // when
        Collections.rotate(list, 2)

        // then
        assertThat(list).isEqualTo(listOf(4, 5, 1, 2, 3))
    }

    @Test
    internal fun `rotates a list left using subList`() {
        // given
        val list = listOf(1, 2, 3, 4, 5)

        // when
        val listRotatedLeft = list.rotateLeftUsingSubList(2)

        // then
        assertThat(listRotatedLeft).isEqualTo(listOf(3, 4, 5, 1, 2))
    }

    @Test
    internal fun `rotates a list right using subList`() {
        // given
        val list = listOf(1, 2, 3, 4, 5)

        // when
        val listRotatedRight = list.rotateRightUsingSubList(2)

        // then
        assertThat(listRotatedRight).isEqualTo(listOf(4, 5, 1, 2, 3))
    }

    @Test
    internal fun `rotates a list left using drop and take`() {
        // given
        val list = setOf(1, 2, 3, 4, 5)

        // when
        val listRotatedLeft = list.rotateLeftUsingDrop(2)

        // then
        assertThat(listRotatedLeft).isEqualTo(listOf(3, 4, 5, 1, 2))
    }

    @Test
    internal fun `rotates a list right using drop and take`() {
        // given
        val list = listOf(1, 2, 3, 4, 5)

        // when
        val listRotatedRight = list.rotateRightUsingDrop(2)

        // then
        assertThat(listRotatedRight).isEqualTo(listOf(4, 5, 1, 2, 3))
    }
}