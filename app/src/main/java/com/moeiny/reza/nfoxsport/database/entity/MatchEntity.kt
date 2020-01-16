package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Matches")
class MatchEntity (@PrimaryKey var match_Id: String,
                   @ColumnInfo var teamA_Id: Int,
                   @ColumnInfo var teamB_Id: Int,
                   @ColumnInfo var stat_type: String)