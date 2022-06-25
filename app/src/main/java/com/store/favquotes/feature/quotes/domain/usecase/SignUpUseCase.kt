package com.store.favquotes.feature.quotes.domain.usecase

import com.store.favquotes.feature.quotes.domain.QuotesRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String) =
        repository.signUp(
            userName = username,
            email = email,
            password = password,
        )
}
