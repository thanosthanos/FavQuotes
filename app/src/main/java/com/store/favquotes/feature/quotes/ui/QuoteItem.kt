package com.store.favquotes.feature.quotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import com.store.favquotes.R
import com.store.favquotes.feature.quotes.domain.model.Quote

@Composable
fun QuoteItem(
    quote: Quote
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(0.8f),
            text = getAnnotatedQuote(quote = quote),
            style = MaterialTheme.typography.subtitle1,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.FavoriteBorder, null,
                    tint = MaterialTheme.colors.error
                )
                Text(
                    text = stringResource(id = R.string.favorites, quote.favoritesCount),
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun getAnnotatedQuote(
    quote: Quote
): AnnotatedString {
    val source = quote.body + " - " + quote.author
    val builder = AnnotatedString.Builder()
    builder.append(source)
    val start = source.indexOf(quote.body)
    val end = start + quote.body.length
    val hyperlinkStyle = SpanStyle(
        color = MaterialTheme.colors.primary,
    )
    builder.addStyle(hyperlinkStyle, start, end)
    builder.addStringAnnotation("TAG", source, start, end)

    return builder.toAnnotatedString()
}

@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    QuoteItem(
        quote = Quote(
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
    )
}
