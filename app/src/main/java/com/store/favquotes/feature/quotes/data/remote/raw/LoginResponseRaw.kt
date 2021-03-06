package com.store.favquotes.feature.quotes.data.remote.raw

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseRaw(
    val login: String?,
    val email: String?,
    val message: String?,
)
