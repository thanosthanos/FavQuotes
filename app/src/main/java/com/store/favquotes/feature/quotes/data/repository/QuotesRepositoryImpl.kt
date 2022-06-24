package com.store.favquotes.feature.quotes.data.repository

import com.store.favquotes.feature.quotes.data.remote.api.QuotesApi
import com.store.favquotes.feature.quotes.domain.QuotesRepository
import com.store.favquotes.feature.quotes.domain.model.Quote
import timber.log.Timber

class QuotesRepositoryImpl(
    private val api: QuotesApi,
) : QuotesRepository {
    override suspend fun getRandomQuote(

    ): Quote {
        try {
            val quotes = api.getQuotes()
            Timber.d("")
        } catch (exc: Exception) {
            Timber.d("")
        }

        return Quote(
            tags = listOf(),
            isFavorite = true,
            authorPermalink = "",
            body = "",
            id = 12,
            favoritesCount = 1,
            upvotesCount = 2,
            downvotesCount = 1,
            dialogue = true,
            author = "",
            url = "",
        )
    }

}
