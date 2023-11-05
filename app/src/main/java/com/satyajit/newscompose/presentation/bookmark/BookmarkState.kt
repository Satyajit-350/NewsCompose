package com.satyajit.newscompose.presentation.bookmark

import com.satyajit.newscompose.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
) {
}