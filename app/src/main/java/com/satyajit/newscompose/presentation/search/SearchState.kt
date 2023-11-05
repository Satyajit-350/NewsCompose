package com.satyajit.newscompose.presentation.search

import androidx.paging.PagingData
import com.satyajit.newscompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery : String = "",
    val article: Flow<PagingData<Article>>?=null
) {
}