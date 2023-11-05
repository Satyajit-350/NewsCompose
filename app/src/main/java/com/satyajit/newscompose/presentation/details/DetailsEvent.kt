package com.satyajit.newscompose.presentation.details

import com.satyajit.newscompose.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()
}