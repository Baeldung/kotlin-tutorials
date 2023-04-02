package com.baeldung.dokka


/**
 * A blog of *articles*.
 *
 * This class it's just a documentation example.
 *
 * @param T the type of article in this blog.
 * @property name the name of this article.
 * @constructor Creates an empty blog.
 */
class Blog<T>(val name: String) {

    /**
     * The variable containing the articles.
     */
    private var articles: MutableList<T> = mutableListOf()

    /**
     * Adds an [article] to this blog.
     * @return the new number of articles of the blog.
     */
    fun add(article: T): Int {
        articles.add(article)
        return articles.size
    }

    /**
     * Remove an [article] to this blog.
     * @return the new number of articles of the blog.
     */
    fun remove(article: T): Int {
        articles.remove(article)
        return articles.size
    }
}


