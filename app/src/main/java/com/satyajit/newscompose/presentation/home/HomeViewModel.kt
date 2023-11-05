package com.satyajit.newscompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.satyajit.newscompose.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    val news = newsUseCases.getNews(
        sources = listOf("google-news-in", "the-hindu", "the-times-of-india")
    ).cachedIn(viewModelScope) //this is used to handle the configuration changes of the device


}