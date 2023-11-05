package com.satyajit.newscompose.domain.usecases.news

import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}