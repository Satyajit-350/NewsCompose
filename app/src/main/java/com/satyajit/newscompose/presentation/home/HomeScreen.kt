package com.satyajit.newscompose.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.satyajit.newscompose.R
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.presentation.Dimens.MediumPadding1
import com.satyajit.newscompose.presentation.common.ArticleList
import com.satyajit.newscompose.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val title by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "  \uD83D\uDD34 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.news_app_logo),
            contentDescription = null,
            modifier = Modifier
                .width(90.dp)
                .height(40.dp)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1, end = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = 3.dp),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}