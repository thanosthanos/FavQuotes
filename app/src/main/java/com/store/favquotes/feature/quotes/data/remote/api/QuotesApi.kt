package com.store.favquotes.feature.quotes.data.remote.api

import com.store.favquotes.feature.quotes.data.remote.raw.*
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
    suspend fun login(@Body user: UserRequestRaw): LoginResponseRaw

    @POST("users")
    suspend fun signUp(@Body user: UserSignUpRequestRaw): SignUpResponseRaw
}
