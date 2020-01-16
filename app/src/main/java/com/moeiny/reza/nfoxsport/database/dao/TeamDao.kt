package com.moeiny.reza.nfoxsport.database.dao

import androidx.room.*

import com.moeiny.reza.nfoxsport.database.entitiy.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM Teams")
    fun getAll(): List<TeamEntity>

    @Query("SELECT * FROM Teams WHERE team_Id = :id")
    fun findByTeamId(id: Int): TeamEntity

    @Query("DELETE FROM Teams")
    fun deleteAll()

    @Insert
    fun insert(team: TeamEntity)

    @Update
    fun update(team: TeamEntity)

    @Delete
    fun delete(team: TeamEntity)
}




