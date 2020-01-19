package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Matches")
class MatchEntity (@PrimaryKey (autoGenerate = true) var Id:Int,
                   @ColumnInfo var match_Id: String,
                   @ColumnInfo var teamA_Id: Int,
                   @ColumnInfo var teamB_Id: Int,
                   @ColumnInfo var stat_type: String){
    constructor(match_id:String, teamA_id:Int, teamB_id:Int,stat_type:String):this(0,match_id,teamA_id,teamB_id,stat_type)
}