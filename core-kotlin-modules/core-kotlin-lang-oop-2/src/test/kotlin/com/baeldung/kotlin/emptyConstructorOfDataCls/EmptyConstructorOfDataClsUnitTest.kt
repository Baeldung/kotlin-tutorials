package com.baeldung.kotlin.emptyConstructorOfDataCls

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


data class ArticleWithoutDefault(
    var title: String,
    var author: String,
    var abstract: String,
    var words: Long
) {
    constructor() : this("dummy title", "dummy author", "dummy abstract", 0)
}

data class ArticleWithDefault(
    var title: String = "default title",
    var author: String = "default author",
    var abstract: String = "",
    var words: Long = 0L
)

// ------- for no-arg plugin
annotation class NoArg

@NoArg
data class Person(var firstName: String = "a nice name", var midName: String?, var lastName: String, var age: Int)

class EmptyConstructorOfDataClsUnitTest {
    @Test
    fun `Given a data class with an empty constructor, when call the empty constructor, should get expected instance`() {
        val myInstance = ArticleWithoutDefault()
        assertThat(myInstance).isInstanceOf(ArticleWithoutDefault::class.java)
            .extracting("title", "author", "abstract", "words")
            .containsExactly("dummy title", "dummy author", "dummy abstract", 0L)
    }

    @Test
    fun `Given a data class with default values and without an empty constructor, when call the empty constructor, should get expected instance`() {
        val myInstance = ArticleWithDefault()
        assertThat(myInstance).isInstanceOf(ArticleWithDefault::class.java)
            .extracting("title", "author", "abstract", "words")
            .containsExactly("default title", "default author", "", 0L)

        val myArticle = ArticleWithDefault(title = "A Great Article", words = 42L)
        assertThat(myArticle).isInstanceOf(ArticleWithDefault::class.java)
            .extracting("title", "author", "abstract", "words")
            .containsExactly("A Great Article", "default author", "", 42L)
    }

    @Test
    fun `Given a data class without default values and empty constructor, when call the empty constructor in java reflection, should get expected instance`() {
        //  val person = Person() //this won't compile
        //  val person = Person::class.createInstance() // error: no zero-parameter constructor defined.
        val myInstance = Person::class.java.getConstructor().newInstance()
        assertThat(myInstance).isInstanceOf(Person::class.java)
            .extracting("firstName", "midName", "lastName", "age").containsExactly(null, null, null, 0)

    }
}