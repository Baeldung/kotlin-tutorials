package com.baeldung.sequeces

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import java.time.Instant

class SequencesUnitTest {

    @Test
    fun shouldBuildSequenceWhenUsingFromElements() {
        val seqOfElements = sequenceOf("first", "second", "third")
            .toList()
        assertEquals(3, seqOfElements.count())
    }

    @Test
    fun shouldBuildSequenceWhenUsingFromFunction() {
        val seqFromFunction = generateSequence(Instant.now()) { it.plusSeconds(1) }
            .take(3)
            .toList()
        assertEquals(3, seqFromFunction.count())
    }

    @Test
    fun shouldBuildSequenceWhenUsingFromChunks() {
        val seqFromChunks = sequence {
            yield(1)
            yieldAll((2..5).toList())
        }.toList()
        assertEquals(5, seqFromChunks.count())
    }

    @Test
    fun shouldBuildSequenceWhenUsingFromCollection() {
        val seqFromIterable = (1..10)
            .asSequence()
            .toList()
        assertEquals(10, seqFromIterable.count())
    }

    @Test
    fun shouldShowNoCountDiffWhenUsingWithAndWithoutSequence() {
        val withSequence = (1..10).asSequence()
            .filter { it % 2 == 1 }
            .map { it * 2 }
            .toList()
        val withoutSequence = (1..10)
            .filter { it % 2 == 1 }
            .map { it * 2 }
        assertEquals(withSequence.count(), withoutSequence.count())
    }

    @Test
    fun shouldNotCreateIntermediateCollections() {
        val sequence = (0..10).asSequence()
        @Suppress("USELESS_IS_CHECK")
        assert(sequence is Sequence)
        val filtered = sequence.filter { it % 2 == 1 }
        @Suppress("USELESS_IS_CHECK")
        assert(filtered is Sequence)
        val mapped = filtered.map { it * 2 }
        @Suppress("USELESS_IS_CHECK")
        assert(mapped is Sequence)
        val list = mapped.toList()
        @Suppress("USELESS_IS_CHECK")
        assert(list is List<Int>)
        assert(list.size == 5)
    }

    @Test
    fun shouldCreateIntermediateCollections() {
        val list = (0..10)
        @Suppress("USELESS_IS_CHECK")
        assert(list is IntRange)
        val filtered = list.filter { it % 2 == 1 }
        @Suppress("USELESS_IS_CHECK")
        assert(filtered is List<Int>)
        val mapped = filtered.map { it * 2 }
        @Suppress("USELESS_IS_CHECK")
        assert(mapped is List<Int>)
        assert(mapped.size == 5)
    }

}