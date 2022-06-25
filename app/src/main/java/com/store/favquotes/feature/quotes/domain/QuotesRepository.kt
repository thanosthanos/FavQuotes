package com.store.favquotes.feature.quotes.domain

import com.store.favquotes.feature.quotes.domain.model.LoginResponse
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.network.ResultWrapper

interface QuotesRepository {
    suspend fun getRandomQuote(): ResultWrapper<Quote>
    suspend fun getQuotes(): ResultWrapper<List<Quote>>
    suspend fun searchQuotes(term: String): ResultWrapper<List<Quote>>
    suspend fun login(
        userNameOrEmail: String,
        password: String
    ): ResultWrapper<LoginResponse>
}
