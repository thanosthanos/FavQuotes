package com.store.favquotes.feature.quotes.data.remote.raw

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponseRaw(
    val login: String?,
    val message: String?,
)
