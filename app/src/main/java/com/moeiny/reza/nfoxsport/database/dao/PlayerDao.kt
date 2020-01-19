package com.moeiny.reza.nfoxsport.database.dao

import androidx.room.*
import com.moeiny.reza.nfoxsport.database.entitiy.PlayerEntity


@Dao
interface PlayerDao {

    @Query("SELECT * FROM Players")
    fun getAll(): List<PlayerEntity>

    @Query("SELECT * FROM Players WHERE team_Id = :id")
    fun findByTeam(id: Int): List<PlayerEntity>

    @Query("SELECT * FROM Players WHERE player_Id = :id AND team_Id=:team_Id")
    fun getPlayer(id: Int,team_Id:Int): PlayerEntity

    @Query("DELETE FROM Players")
    fun deleteAll()

    @Insert
    fun insert(player: PlayerEntity)

    @Update
    fun update(player: PlayerEntity)

    @Delete
    fun delete(player: PlayerEntity)
}




