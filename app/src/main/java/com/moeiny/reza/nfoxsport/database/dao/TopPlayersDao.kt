package com.moeiny.reza.nfoxsport.database.dao

import androidx.room.*

import com.moeiny.reza.nfoxsport.database.entitiy.TopPlayerEntity

@Dao
interface TopPlayersDao {

    @Query("SELECT * FROM TopPlayers")
    fun getAll(): List<TopPlayerEntity>

    @Query("SELECT * FROM TopPlayers WHERE match_Id = :match_id AND match_type = :match_type AND team_Id = :team_id")
    fun findBymatchteam(match_id: String,match_type: String,team_id: Int): List<TopPlayerEntity>

    @Query("DELETE FROM TopPlayers")
    fun deleteAll()

    @Insert
    fun insert(topPlayer: TopPlayerEntity)

    @Update
    fun update(topPlayer: TopPlayerEntity)

    @Delete
    fun delete(topPlayer: TopPlayerEntity)
}




