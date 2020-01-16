package com.moeiny.reza.nfoxsport.presenter

import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback


interface IMatchService {

    fun getMatchesInfo(block: FoxSportCallback<List<Match>, Throwable>)

    fun getStatsInfo(block: FoxSportCallback<Stats, Throwable>)

}