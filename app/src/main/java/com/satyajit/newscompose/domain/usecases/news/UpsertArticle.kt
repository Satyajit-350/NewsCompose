package com.satyajit.newscompose.domain.usecases.news

import com.satyajit.newscompose.data.local.NewsDao
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}