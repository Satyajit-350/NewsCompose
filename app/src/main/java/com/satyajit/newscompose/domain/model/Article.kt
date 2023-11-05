package com.satyajit.newscompose.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
): Parcelable {
    val getDate @SuppressLint("SimpleDateFormat")
    get() : String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("dd-MM-yyyy")

        return try {
            val date = inputFormat.parse(publishedAt)
            outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            "Invalid Date"
        }
    }
}