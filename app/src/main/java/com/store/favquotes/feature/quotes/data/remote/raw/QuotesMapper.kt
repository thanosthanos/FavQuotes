package com.store.favquotes.feature.quotes.data.remote.raw

import com.store.favquotes.feature.quotes.domain.model.Quote


fun List<QuoteRaw>.toQuotes(): List<Quote> = map { it.toQuote() }

fun QuoteRaw.toQuote(): Quote = Quote(
    tags = tags,
    isFavorite = favorite,
    authorPermalink = author_permalink,
    body = body,
    id = id,
    favoritesCount = favorites_count,
    upvotesCount = upvotes_count,
    downvotesCount = downvotes_count,
    dialogue = dialogue,
    author = author,
    url = url,
)
