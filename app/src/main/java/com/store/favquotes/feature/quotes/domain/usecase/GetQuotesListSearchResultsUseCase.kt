package com.store.favquotes.feature.quotes.domain.usecase

import com.store.favquotes.feature.quotes.domain.QuotesRepository
import javax.inject.Inject

class GetQuotesListSearchResultsUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(searchTerm: String) = repository.searchQuotes(term = searchTerm)
}
