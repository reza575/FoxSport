package com.moeiny.reza.nfoxsport.model.repository



import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.*

interface MatchApiService {

    @GET
    fun getMatchesInfo(@Url url: String): Flowable<List<Match>>

    @GET
    fun getStatsInfo(@Url url: String): Flowable<Stats>


    companion object Factory {
        fun create(retrofit: Retrofit): MatchApiService {
            return retrofit.create(MatchApiService::class.java)
        }
    }
}