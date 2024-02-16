package com.baeldung.kdoc

/**
 * A blog of *articles*.
 *
 * This class is just a **documentation example**.
 *
 * @param T the type of article in this blog.
 * @property name the name of this blog.
 * @constructor creates an empty blog.
 * @see com.baeldung.kdoc.TitledArticle
 * @sample com.baeldung.kdoc.blogSample
 */
class Blog<T>(private val name: String) {

    private var articles: MutableList<T> = mutableListOf()

    /**
     * Adds an [article] to this blog.
     * @return the new number of articles of the blog.
     */
    fun add(article: T): Int {
        articles.add(article)
        return articles.size
    }
}

/**
 * An article with a title.
 *
 * @property title the title of this article.
 * @constructor creates an article with a title.
 */
data class TitledArticle(private val title: String)

private fun blogSample() {
    val blog = Blog<TitledArticle>("Baeldung on Kotlin")
    val blogCount = blog.add(TitledArticle("An Introduction to KDoc"))
    println("Blog count is: $blogCount")
}

