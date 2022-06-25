package com.store.favquotes.feature.quotes.domain.usecase

import com.store.favquotes.feature.quotes.domain.QuotesRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(userNameOrEmail: String, password: String) = repository.login(
        userNameOrEmail = userNameOrEmail,
        password = password,
    )
}
