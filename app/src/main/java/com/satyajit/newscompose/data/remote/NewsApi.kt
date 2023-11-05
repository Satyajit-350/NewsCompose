package com.satyajit.newscompose.data.remote

import com.satyajit.newscompose.BuildConfig
import com.satyajit.newscompose.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse
}