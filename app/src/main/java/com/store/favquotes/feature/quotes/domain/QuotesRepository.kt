package com.store.favquotes.feature.quotes.domain

import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.network.ResultWrapper

interface QuotesRepository {
    suspend fun getRandomQuote(): ResultWrapper<Quote>
}
