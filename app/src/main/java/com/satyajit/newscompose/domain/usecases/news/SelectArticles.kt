package com.satyajit.newscompose.domain.usecases.news

import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}