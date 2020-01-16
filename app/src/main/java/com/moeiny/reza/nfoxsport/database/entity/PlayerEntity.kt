package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Players")
class PlayerEntity (@PrimaryKey var player_Id: Int,
                    @ColumnInfo var team_Id: String,
                    @ColumnInfo var player_fullname: String,
                    @ColumnInfo var player_jumpernumber: Int,
                    @ColumnInfo var player_shortname: String,
                    @ColumnInfo var player_position: String,
                    @ColumnInfo var player_statvalue: Int)

