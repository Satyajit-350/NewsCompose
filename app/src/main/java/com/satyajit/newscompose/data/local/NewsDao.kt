package com.satyajit.newscompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.satyajit.newscompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article) //update and insert both

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT*FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT*FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): Article?

}