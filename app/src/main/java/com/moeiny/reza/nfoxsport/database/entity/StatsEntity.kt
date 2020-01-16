package com.moeiny.reza.nfoxsport.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Stats")
class StatsEntity (@PrimaryKey var player_Id: Int,
                   @ColumnInfo var surName: String,
                   @ColumnInfo var position: String,
                   @ColumnInfo var full_name: String,
                   @ColumnInfo var short_name: String,
                   @ColumnInfo var date_Of_birth: String,
                   @ColumnInfo var height_cm: Int,
                   @ColumnInfo var other_name: String,
                   @ColumnInfo var weight_kg: Int,
                   @ColumnInfo var last_match_id: String,
                   @ColumnInfo var carier_Stats: String,
                   @ColumnInfo var last_match_stats: String,
                   @ColumnInfo var lseries_season_stats: String)

