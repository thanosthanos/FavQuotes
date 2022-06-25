package com.store.favquotes.feature.quotes.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import com.store.favquotes.R
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.ui.theme.spacing

enum class ExpandableState { VISIBLE, HIDDEN }

@Composable
fun QuoteItem(
    quote: Quote,
    defaultState: ExpandableState = ExpandableState.HIDDEN,
) {
    var isExpandedContentVisible by remember { mutableStateOf(defaultState) }

    Column(
        modifier = Modifier.clickable {
            isExpandedContentVisible = if (isExpandedContentVisible == ExpandableState.VISIBLE)
                ExpandableState.HIDDEN
            else ExpandableState.VISIBLE
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
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
        AnimatedVisibility(visible = isExpandedContentVisible == ExpandableState.VISIBLE) {
            ExpandableContent(quote = quote)
        }
    }
}

@Composable
fun ExpandableContent(quote: Quote) {
    Column(modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)) {

        if (quote.tags.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.spacing.medium)
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small),
                    text = stringResource(id = R.string.tags_title),
                    color = MaterialTheme.colors.primaryVariant
                )
                quote.tags.forEachIndexed { index, tag ->
                    Text(
                        tag,
                        color = MaterialTheme.colors.onSurface
                    )

                    if (index != quote.tags.lastIndex) {
                        Text(
                            modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                            text = ",",
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
        quote.upvotesCount?.let {
            Row(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                    text = quote.upvotesCount.toString(),
                    style = MaterialTheme.typography.subtitle1,
                )
                Icon(
                    Icons.Filled.ThumbUp, null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        quote.downvotesCount?.let {
            Row(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                    text = quote.downvotesCount.toString(),
                    style = MaterialTheme.typography.subtitle1,
                )
                Icon(
                    Icons.Filled.ThumbDown, null,
                    tint = MaterialTheme.colors.error
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
            tags = listOf("tag1", "tag2"),
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
        defaultState = ExpandableState.VISIBLE,
    )
}
