package com.store.favquotes.feature.quotes.data.remote.api

import com.store.favquotes.feature.quotes.data.remote.raw.QuotesResponseRaw
import retrofit2.http.GET

interface QuotesApi {

    @GET("quotes")
    suspend fun getQuotes(): QuotesResponseRaw

}
