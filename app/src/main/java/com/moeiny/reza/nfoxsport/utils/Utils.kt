package com.moeiny.reza.nfoxsport.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


enum class API(val value: String) {
    KEY(""),
    TIMEOUT_IN_MS("30000"),
    BASE_URL("https://statsapi.foxsports.com.au" ),
    GET_MATCHES_URL("/3.0/api/sports/league/matches/NRL20190101/topplayerstats.json;type=" +
            "fantasy_points;type=tackles;type=runs;type=run_metres?limit=5&userkey=A00239D3-45F6-4A0A-810C-54A347F144C2"),
    GET_STATS_URL("/3.0/api/sports/league/series/1/seasons/117/teams/@team_Id/players/@player_Id/" +
            "detailedstats.json?&userkey=9024ec15-d791-4bfd-aa3b-5bcf5d36da4f") ,
    GET_PLAYERIMAGE_URL("https://media.foxsports.com.au/match-centre/includes/images/headshots/landscape/nrl/@player_Id.jpg") ,
    GET_DEFAULTIMAGE_URL("https://media.foxsports.com.au/match-centre/includes/images/headshots/headshot-blank-large.jpg")
}

fun okHttpClientGETBuilder() : OkHttpClient {

    val clientBuilder = OkHttpClient.Builder()

   // Create a new Interceptor.
    val headerAuthorizationInterceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            request = request.newBuilder().build()
            return chain.proceed(request)
        }
    }

    //Add the interceptor to the client builder.
    clientBuilder.addNetworkInterceptor(headerAuthorizationInterceptor)
    return clientBuilder.build()
}

fun retrofit(httpClient: OkHttpClient): Retrofit {
    val retrofit = Retrofit.Builder()
            .baseUrl(API.BASE_URL.value)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    return retrofit
}

open interface FoxSportCallback<in T, in R> {
    fun onSuccess(result: T)
    fun onError(error: R?)
    fun onComplete()
}