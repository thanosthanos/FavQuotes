package com.store.favquotes.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject


internal const val HTTP_HEADER_AUTHORIZATION = "Authorization"
internal const val HTTP_HEADER_BEARER_TOKEN_PREFIX = "Bearer "
internal const val TOKEN = "9ea4bd589f41521ceb69b17bea07665e"

class AuthorizationInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().markItAsSigned()?.let {
            chain.proceed(it)
        } ?: chain.proceed(chain.request())
    }

    private fun Request.markItAsSigned(): Request? {
        return try {
            newBuilder()
                .header(
                    name = HTTP_HEADER_AUTHORIZATION,
                    value = HTTP_HEADER_BEARER_TOKEN_PREFIX + TOKEN
                )
                .build()
        } catch (nsee: NoSuchElementException) {
            Timber.d(nsee)
            null
        }
    }
}
