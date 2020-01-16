package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "TopPlayers", primaryKeys = ["match_Id","team_Id","match_type","player_Id"])
class TopPlayerEntity (@ColumnInfo var match_Id: String,
                       @ColumnInfo var match_type: String,
                       @ColumnInfo var team_Id: Int,
                       @ColumnInfo var player_Id: Int)