package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "TopPlayers")
class TopPlayerEntity (@PrimaryKey (autoGenerate = true) var Id: Int,
                       @ColumnInfo var match_Id: String,
                       @ColumnInfo var match_type: String,
                       @ColumnInfo var team_Id: Int,
                       @ColumnInfo var player_Id: Int,
                       @ColumnInfo var player_fullname: String,
                       @ColumnInfo var player_jumpernumber: Int,
                       @ColumnInfo var player_shortname: String,
                       @ColumnInfo var player_position: String,
                       @ColumnInfo var player_statvalue: Int){
    constructor(match_id:String, match_type:String, team_id:Int,player_Id:Int,player_fullname: String,
                player_jumpernumber: Int,player_shortname: String,player_position: String,player_statvalue: Int):
            this(0,match_id,match_type,team_id,player_Id,player_fullname,player_jumpernumber,player_shortname,
                player_position,player_statvalue)

}