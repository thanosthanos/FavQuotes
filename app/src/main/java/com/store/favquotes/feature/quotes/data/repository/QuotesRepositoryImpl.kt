package com.store.favquotes.feature.quotes.data.repository

import com.store.favquotes.feature.quotes.data.remote.api.QuotesApi
import com.store.favquotes.feature.quotes.data.remote.raw.UserRaw
import com.store.favquotes.feature.quotes.data.remote.raw.UserRequestRaw
import com.store.favquotes.feature.quotes.data.remote.raw.toLoginResponse
import com.store.favquotes.feature.quotes.data.remote.raw.toQuotes
import com.store.favquotes.feature.quotes.domain.QuotesRepository
import com.store.favquotes.feature.quotes.domain.model.LoginResponse
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.network.ResultWrapper
import com.store.favquotes.network.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class QuotesRepositoryImpl(
    private val api: QuotesApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : QuotesRepository {

    override suspend fun getRandomQuote(): ResultWrapper<Quote> {
        return safeApiCall(dispatcher) { api.getQuotes().quotes.toQuotes().random() }
    }

    override suspend fun getQuotes(): ResultWrapper<List<Quote>> {
        return safeApiCall(dispatcher) { api.getQuotes().quotes.toQuotes() }
    }

    override suspend fun searchQuotes(term: String): ResultWrapper<List<Quote>> {
        return safeApiCall(dispatcher) { api.searchQuotes(term = term).quotes.toQuotes() }
    }

    override suspend fun login(
        userNameOrEmail: String,
        password: String
    ): ResultWrapper<LoginResponse> {
        return safeApiCall(dispatcher) {
            api.login(
                user = UserRequestRaw(
                    UserRaw(
                        login = userNameOrEmail,
                        password = password
                    )
                )
            ).toLoginResponse()
        }
    }

}
