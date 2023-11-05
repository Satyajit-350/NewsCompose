package com.satyajit.newscompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.satyajit.newscompose.domain.model.Article
import java.lang.Exception

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val sources: String
): PagingSource<Int,Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?:1
        return try{
            val newsResponse = newsApi.searchNews(searchQuery = searchQuery,page = page, sources = sources)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } //remove duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if(totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }


}