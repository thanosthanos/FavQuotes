package com.store.favquotes.feature.quotes.data.remote.raw

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSignUpRaw (
    val login: String?,
    val email: String?,
    val password: String,
)
