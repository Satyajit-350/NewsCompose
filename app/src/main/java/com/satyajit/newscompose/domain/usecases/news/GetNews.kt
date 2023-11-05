package com.satyajit.newscompose.domain.usecases.news

import androidx.paging.PagingData
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources)
    }


}