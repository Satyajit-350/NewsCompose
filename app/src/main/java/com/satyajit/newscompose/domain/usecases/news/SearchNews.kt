package com.satyajit.newscompose.domain.usecases.news

import androidx.paging.PagingData
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery,sources)
    }

}