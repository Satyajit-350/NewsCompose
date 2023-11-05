package com.satyajit.newscompose.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) :SearchEvent()

    object SearchNews : SearchEvent()
}