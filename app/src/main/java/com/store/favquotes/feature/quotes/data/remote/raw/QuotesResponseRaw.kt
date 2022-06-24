package com.store.favquotes.feature.quotes.data.remote.raw

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuotesResponseRaw(
    val quotes: List<QuoteRaw>
)
