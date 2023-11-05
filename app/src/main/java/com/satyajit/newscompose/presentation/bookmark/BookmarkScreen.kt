package com.satyajit.newscompose.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satyajit.newscompose.R
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.presentation.Dimens.MediumPadding1
import com.satyajit.newscompose.presentation.common.ArticleList

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = 15.dp,
                start = 15.dp,
                end = 15.dp
            )
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(articles = state.articles, onClick = { navigateToDetails(it) })
        
    }

}