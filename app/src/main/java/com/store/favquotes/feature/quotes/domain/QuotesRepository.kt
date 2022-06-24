package com.store.favquotes.feature.quotes.domain

import com.store.favquotes.feature.quotes.domain.model.Quote

interface QuotesRepository {
    suspend fun getRandomQuote(): Quote
}
