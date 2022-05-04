package com.baeldung.exceptionhandling.controller

import com.baeldung.exceptionhandling.model.ArticleModel
import com.baeldung.exceptionhandling.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/articles")
class ArticleController(val articleService: ArticleService) {

    @PostMapping()
    fun createArticle(@RequestBody title: String): ArticleModel {

        return articleService.createArticle(title);
    }

    @GetMapping()
    fun getArticle(@RequestParam id: String): ArticleModel {

        return articleService.getArticle(id);
    }

    @PutMapping()
    fun updateArticle(@RequestParam id: String, @RequestParam title: String): ArticleModel {

        try {
            return articleService.updateArticle(id, title);
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.localizedMessage, ex)
        }
    }
}