package com.satyajit.newscompose.presentation.onboarding

import androidx.annotation.DrawableRes
import com.satyajit.newscompose.R

data class Page(
    val title:String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Welcome to NewsCompose",
        description = "We're excited to have you! Let's get started and explore the world of news together.",
        image = R.drawable.onboarding_1
    ),
    Page(
        title = "Breaking News Alerts",
        description = "Receive breaking news alerts and be the first to know about important events.",
        image = R.drawable.onboarding_2
    ),
    Page(
        title = "Explore Diverse Sources",
        description = "Get news from a wide range of sources, including top publications and local news outlets.",
        image = R.drawable.onboarding_3
    )
)
