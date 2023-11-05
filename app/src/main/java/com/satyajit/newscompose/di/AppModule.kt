package com.satyajit.newscompose.di

import android.app.Application
import androidx.room.Room
import com.satyajit.newscompose.BuildConfig
import com.satyajit.newscompose.data.local.NewsDao
import com.satyajit.newscompose.data.local.NewsDatabase
import com.satyajit.newscompose.data.local.NewsTypeConverter
import com.satyajit.newscompose.data.manager.LocalUserManagerImplementation
import com.satyajit.newscompose.data.remote.NewsApi
import com.satyajit.newscompose.data.repository.NewsRepositoryImplementation
import com.satyajit.newscompose.domain.manager.LocalUserManager
import com.satyajit.newscompose.domain.repoistory.NewsRepository
import com.satyajit.newscompose.domain.usecases.app_entry.AppEntryUseCases
import com.satyajit.newscompose.domain.usecases.app_entry.ReadAppEntry
import com.satyajit.newscompose.domain.usecases.app_entry.SaveAppEntry
import com.satyajit.newscompose.domain.usecases.news.DeleteArticle
import com.satyajit.newscompose.domain.usecases.news.GetNews
import com.satyajit.newscompose.domain.usecases.news.NewsUseCases
import com.satyajit.newscompose.domain.usecases.news.SearchNews
import com.satyajit.newscompose.domain.usecases.news.SelectArticle
import com.satyajit.newscompose.domain.usecases.news.SelectArticles
import com.satyajit.newscompose.domain.usecases.news.UpsertArticle
import com.satyajit.newscompose.util.Constants.NEWS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImplementation(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImplementation(newsApi,newsDao)

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}