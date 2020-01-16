package com.moeiny.reza.nfoxsport.model.repository





import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.utils.API
import io.reactivex.Flowable

class MatchRepository(private val matchApiService: MatchApiService) {

    fun getMatchesInfo(): Flowable<List<Match>> {
        return matchApiService.getMatchesInfo(API.GET_MATCHES_URL.value)
    }

    fun getStatsInfo(team_Id:String,player_Id:String): Flowable<Stats> {
        return matchApiService.getStatsInfo(API.GET_STATS_URL.value.replace("@team_Id",team_Id)
                                                                   .replace("@player_Id",player_Id))
    }
}