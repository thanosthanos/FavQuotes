package com.store.favquotes.feature.quotes.domain.usecase

import com.store.favquotes.feature.quotes.domain.QuotesRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke() = repository.getRandomQuote()
}
