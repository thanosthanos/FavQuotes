package com.store.favquotes.feature.quotes.di

import com.store.favquotes.feature.quotes.data.remote.api.QuotesApi
import com.store.favquotes.feature.quotes.data.repository.QuotesRepositoryImpl
import com.store.favquotes.feature.quotes.domain.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuotesModule {

    @Provides
    @Singleton
    fun provideQuotesApiService(retrofit: Retrofit) =
        retrofit.create(QuotesApi::class.java)

    @Provides
    @Singleton
    fun provideQuotesRepository(
        quotesApi: QuotesApi,
    ): QuotesRepository = QuotesRepositoryImpl(
        api = quotesApi
    )

}
