package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.ui.theme.spacing

@Composable
fun QuotesList(
    quotes: List<Quote>
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.medium)
    ) {
        items(quotes) { quote ->
            QuoteItem(quote = quote)
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.medium)
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuotesListPreview() {
    QuotesList(
        quotes = listOf(
            Quote(
                tags = listOf(),
                isFavorite = false,
                authorPermalink = "",
                body = "This is a quote",
                id = 12,
                favoritesCount = 1,
                upvotesCount = 1,
                downvotesCount = 1,
                dialogue = false,
                author = "Tolstoi",
                url = "",
            ),
            Quote(
                tags = listOf(),
                isFavorite = false,
                authorPermalink = "",
                body = "This is a quote 2",
                id = 12,
                favoritesCount = 1,
                upvotesCount = 1,
                downvotesCount = 1,
                dialogue = false,
                author = "Dosto",
                url = "",
            ),
        )
    )
}
