package com.store.favquotes.feature.quotes.data.remote.raw

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteRaw(
    val tags: List<String>,
    val favorite: Boolean?,
    val author_permalink: String,
    val body: String,
    val id: Int,
    val favorites_count: Int,
    val upvotes_count: Int,
    val downvotes_count: Int,
    val dialogue: Boolean,
    val author: String,
    val url: String,
)
