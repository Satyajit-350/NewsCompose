package com.satyajit.newscompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.satyajit.newscompose.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao
}