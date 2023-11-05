package com.satyajit.newscompose.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satyajit.newscompose.R
import com.satyajit.newscompose.domain.model.Article
import com.satyajit.newscompose.domain.model.Source
import com.satyajit.newscompose.presentation.Dimens.MediumPadding1
import com.satyajit.newscompose.presentation.details.components.DetailsTopBar
import com.satyajit.newscompose.ui.theme.NewsComposeTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                top = 15.dp
            )
        ) {
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.text_title)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsComposeTheme {
        DetailsScreen(
            article = Article(
                author = "",
                title = "Preview",
                description = "This is a preview description",
                content = "content",
                publishedAt = "2023-06-16T22:24:33Z",
                source = Source("", "bbc"),
                url = "",
                urlToImage = ""
            ),
            event = {}
        ) {

        }
    }
}