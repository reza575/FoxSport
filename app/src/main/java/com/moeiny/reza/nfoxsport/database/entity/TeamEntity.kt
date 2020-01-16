package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Teams")
class TeamEntity (@PrimaryKey var team_Id: Int,
                  @ColumnInfo var team_code: String,
                  @ColumnInfo var team_name: String,
                  @ColumnInfo var team_shortname: String)

