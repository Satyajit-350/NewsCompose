package com.satyajit.newscompose.data.remote.dto

import com.satyajit.newscompose.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)