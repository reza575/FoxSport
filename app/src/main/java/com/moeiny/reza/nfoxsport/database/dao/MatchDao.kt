package com.moeiny.reza.nfoxsport.database.dao

import androidx.room.*
import com.moeiny.reza.nfoxsport.database.entitiy.MatchEntity

@Dao
interface MatchDao {

    @Query("SELECT * FROM Matches")
    fun getAll(): List<MatchEntity>

    @Query("SELECT * FROM Matches WHERE stat_type = :type")
    fun findByType(type: String): MatchEntity

    @Query("SELECT * FROM matches WHERE match_Id = :id")
    fun findByMatchId(id: String): MatchEntity

    @Query("DELETE FROM Matches")
    fun deleteAll()

    @Insert
    fun insert(match: MatchEntity)

    @Update
    fun update(match: MatchEntity)

    @Delete
    fun delete(match: MatchEntity)
}




