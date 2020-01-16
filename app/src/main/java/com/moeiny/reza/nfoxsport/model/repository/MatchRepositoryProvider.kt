package com.moeiny.reza.nfoxsport.model.repository

import retrofit2.Retrofit

object MatchRepositoryProvider {

    fun provideMatchRepository(retrofit: Retrofit) : MatchRepository {
        return MatchRepository(MatchApiService.create(retrofit))
    }
}