package com.store.favquotes.feature.quotes.data.remote.api

import com.store.favquotes.feature.quotes.data.remote.raw.LoginResponseRaw
import com.store.favquotes.feature.quotes.data.remote.raw.QuotesResponseRaw
import com.store.favquotes.feature.quotes.data.remote.raw.UserRaw
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuotesApi {

    @GET("quotes")
    suspend fun getQuotes(): QuotesResponseRaw

    @GET("quotes")
    suspend fun searchQuotes(
        @Query("filter") term: String
    ): QuotesResponseRaw

    @POST("session")
    suspend fun login(@Body user: UserRaw): LoginResponseRaw
}
