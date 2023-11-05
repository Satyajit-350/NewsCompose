package com.satyajit.newscompose.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.presentation.Dimens.MediumPadding1
import com.satyajit.newscompose.presentation.common.ArticleList
import com.satyajit.newscompose.presentation.common.SearchBar
import com.satyajit.newscompose.presentation.navgraph.Routes

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = 5.dp,
                end = 5.dp
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.article?.let {
            val article = it.collectAsLazyPagingItems()
            ArticleList(
                articles = article,
                onClick = { data ->
                    navigateToDetails(data)
                }
            )
        }
    }

}