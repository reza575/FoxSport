package com.moeiny.reza.nfoxsport.model.entity

data class Match(
    val match_id: String,
    val stat_type: String,
    val team_A: TeamA,
    val team_B: TeamB
)