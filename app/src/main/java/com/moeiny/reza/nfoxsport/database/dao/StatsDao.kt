package com.moeiny.reza.nfoxsport.database.dao

import androidx.room.*

import com.moeiny.reza.nfoxsport.database.entitiy.StatsEntity

@Dao
interface StatsDao {

    @Query("SELECT * FROM Stats")
    fun getAll(): List<StatsEntity>

    @Query("SELECT * FROM Stats WHERE player_Id = :id")
    fun findByPlayerId(id: Int): StatsEntity

    @Query("DELETE FROM Stats")
    fun deleteAll()

    @Insert
    fun insert(stats: StatsEntity)

    @Update
    fun update(stats: StatsEntity)

    @Delete
    fun delete(stats: StatsEntity)
}




