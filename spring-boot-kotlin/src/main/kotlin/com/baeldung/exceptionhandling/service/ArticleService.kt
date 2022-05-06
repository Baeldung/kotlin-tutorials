package com.baeldung.exceptionhandling.service

import com.baeldung.exceptionhandling.exception.ArticleNotFoundException
import com.baeldung.exceptionhandling.model.ArticleModel
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ArticleService {

    lateinit var articles: List<ArticleModel>

    @PostConstruct
    fun buildArticles() {
        articles = listOf(
            ArticleModel("1", "Exception Handling in Kotlin"),
            ArticleModel("2", "Decorator Patter in Kotlin"),
            ArticleModel("3", "Abstract Pattern in Kotlin")
        )
    }

    fun createArticle(title: String): ArticleModel {

        val article = (articles.find { articleModel -> articleModel.title == title })

        if (article != null) {
            throw IllegalStateException("Article with the same title already exists")
        }

        return ArticleModel("4", title)
    }

    fun getArticle(id: String): ArticleModel {

        return articles.find { articleModel -> articleModel.id == id }
            ?: throw ArticleNotFoundException("Article not found")
    }

    fun updateArticle(id: String, title: String): ArticleModel {

        val article = (articles.find { articleModel -> articleModel.id == id }
            ?: throw ArticleNotFoundException("Article not found"))

        if (title.length > 50) {
            throw IllegalArgumentException("Article title too long")
        }

        article.title = title

        return article
    }
}