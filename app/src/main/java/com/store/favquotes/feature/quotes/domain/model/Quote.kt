package com.store.favquotes.feature.quotes.domain.model

data class Quote(
    val tags: List<String>,
    val isFavorite: Boolean?,
    val authorPermalink: String?,
    val body: String,
    val id: Int,
    val favoritesCount: Int,
    val upvotesCount: Int?,
    val downvotesCount: Int?,
    val dialogue: Boolean?,
    val author: String?,
    val url: String?,
)
