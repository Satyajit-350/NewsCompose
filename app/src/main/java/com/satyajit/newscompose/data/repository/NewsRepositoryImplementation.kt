package com.satyajit.newscompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.satyajit.newscompose.data.local.NewsDao
import com.satyajit.newscompose.data.remote.NewsApi
import com.satyajit.newscompose.data.remote.NewsPagingSource
import com.satyajit.newscompose.data.remote.SearchNewsPagingSource
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.repoistory.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImplementation(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow

        return pager
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow

        return pager
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article?{
        return newsDao.getArticle(url)
    }


}