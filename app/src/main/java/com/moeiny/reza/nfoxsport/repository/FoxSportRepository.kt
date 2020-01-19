package com.moeiny.reza.nfoxsport.repository

import android.app.Application
import android.os.AsyncTask
import com.moeiny.reza.nfoxsport.database.AppDatabase
import com.moeiny.reza.nfoxsport.database.dao.*
import com.moeiny.reza.nfoxsport.database.entitiy.*


class FoxSportRepository(application: Application){

    private  var matchDao: MatchDao
    private  var playerDao: PlayerDao
    private  var statsDao: StatsDao
    private  var teamDao: TeamDao
    private  var topPlayersDao: TopPlayersDao

    private  var allMatchData:List<MatchEntity>
    private  var allPlayersData:List<PlayerEntity>
    private  var allStatsData:List<StatsEntity>
    private  var allTeamData:List<TeamEntity>
    private  var allTopPlayersData:List<TopPlayerEntity>


    init {
        val db: AppDatabase = AppDatabase.getInstance(
                application.applicationContext )!!

        matchDao = db.MatchDao()
        playerDao = db.PlayerDao()
        statsDao = db.StatsDao()
        teamDao = db.TeamDao()
        topPlayersDao = db.TopPlayersDao()


        allMatchData = matchDao.getAll()
        allPlayersData = playerDao.getAll()
        allStatsData = statsDao.getAll()
        allTeamData = teamDao.getAll()
        allTopPlayersData = topPlayersDao.getAll()


    }

    ////////////////////////////////Match////////////////////////////

    fun insertMatch(matchEntity: MatchEntity){
        MatchInsert(matchDao).execute(matchEntity)
    }

    fun updateMatch(matchEntity: MatchEntity){
        MatchUpdate(matchDao).execute(matchEntity)
    }

    fun deleteMatch(matchEntity: MatchEntity){
        MatchDelete(matchDao).execute(matchEntity)
    }

    fun deleteAllMatch(){
       matchDao.deleteAll()
    }

    fun getAllMatch():List<MatchEntity>{
        return allMatchData
    }

    fun getMatch(match_id:String,stat_type:String):MatchEntity{
        return matchDao.getmatch(match_id,stat_type)
    }

    fun getMatchbyType(stat_type:String):MatchEntity{
        return matchDao.findByType(stat_type)
    }

    private class MatchInsert(matchDao: MatchDao): AsyncTask<MatchEntity, Void, Void>(){

        private var matchDao: MatchDao
        init{
            this.matchDao=matchDao
        }

        override fun doInBackground(vararg p0: MatchEntity): Void? {
            matchDao.insert(p0[0])
            return null
        }

    }

    private class MatchUpdate(matchDao: MatchDao): AsyncTask<MatchEntity, Void, Void>(){

        private var matchDao:MatchDao
        init{
            this.matchDao=matchDao
        }

        override fun doInBackground(vararg p0: MatchEntity): Void? {
            matchDao.update(p0[0])
            return null
        }
    }

    private class MatchDelete(matchDao: MatchDao): AsyncTask<MatchEntity, Void, Void>(){

        private var matchDao:MatchDao
        init{
            this.matchDao=matchDao
        }

        override fun doInBackground(vararg p0: MatchEntity): Void? {
            matchDao.delete(p0[0])
            return null
        }
    }

////////////////////////////////Player////////////////////////////

    fun insertPlayer(playerEntity: PlayerEntity){
        PlayerInsert(playerDao).execute(playerEntity)
    }

    fun updatePlayer(playerEntity: PlayerEntity){
        PlayerUpdate(playerDao).execute(playerEntity)
    }

    fun deletePlayer(playerEntity: PlayerEntity){
        PlayerDelete(playerDao).execute(playerEntity)
    }

    fun deleteAllPlayer(){
        playerDao.deleteAll()
    }

    fun getPlayer(playerId: Int,team_Id:Int):PlayerEntity{
       return playerDao.getPlayer(playerId,team_Id)
    }

    fun getPlayerbyTeam(teamId: Int):List<PlayerEntity>{
        return playerDao.findByTeam(teamId)
    }

    fun getAllPlayer():List<PlayerEntity>{
        return allPlayersData
    }

    private class PlayerInsert(playerDao: PlayerDao): AsyncTask<PlayerEntity, Void, Void>(){

        private var playerDao:PlayerDao
        init{
            this.playerDao=playerDao
        }

        override fun doInBackground(vararg p0: PlayerEntity): Void? {
            playerDao.insert(p0[0])
            return null
        }
    }

    private class PlayerUpdate(playerDao: PlayerDao): AsyncTask<PlayerEntity, Void, Void>(){

        private var playerDao:PlayerDao
        init{
            this.playerDao=playerDao
        }

        override fun doInBackground(vararg p0: PlayerEntity): Void? {
            playerDao.update(p0[0])
            return null
        }
    }

    private class PlayerDelete(playerDao: PlayerDao): AsyncTask<PlayerEntity, Void, Void>(){

        private var playerDao:PlayerDao
        init{
            this.playerDao=playerDao
        }

        override fun doInBackground(vararg p0: PlayerEntity): Void? {
            playerDao.delete(p0[0])
            return null
        }
    }

    //////////////////////////////////////Stats //////////////////////////////////

    fun insertStats(statsEntity: StatsEntity){
        DeviceLogInsert(statsDao).execute(statsEntity)
    }

    fun updateStats(statsEntity: StatsEntity){
        StatsUpdate(statsDao).execute(statsEntity)
    }

    fun deleteStats(statsEntity: StatsEntity){
        StatsDelete(statsDao).execute(statsEntity)
    }

    fun deleteAllStats(){
        statsDao.deleteAll()
    }

    fun getStat(player_id:Int,team_Id:Int):StatsEntity{
        return statsDao.getStat(player_id,team_Id)
    }


    fun getAllStats():List<StatsEntity>{
        return allStatsData
    }

    private class DeviceLogInsert(statsDao: StatsDao): AsyncTask<StatsEntity, Void, Void>(){

        private var statsDao:StatsDao
        init{
            this.statsDao=statsDao
        }

        override fun doInBackground(vararg p0: StatsEntity): Void? {
            statsDao.insert(p0[0])
            return null
        }

    }

    private class StatsUpdate(statsDao: StatsDao): AsyncTask<StatsEntity, Void, Void>(){

        private var statsDao:StatsDao
        init{
            this.statsDao=statsDao
        }

        override fun doInBackground(vararg p0: StatsEntity): Void? {
            statsDao.update(p0[0])
            return null
        }
    }

    private class StatsDelete(statsDao: StatsDao): AsyncTask<StatsEntity, Void, Void>(){

        private var statsDao:StatsDao
        init{
            this.statsDao=statsDao
        }

        override fun doInBackground(vararg p0: StatsEntity): Void? {
            statsDao.delete(p0[0])
            return null
        }
    }

    //////////////////////////////// Team////////////////////////////

    fun insertTeam(teamEntity: TeamEntity){
        TeamInsert(teamDao).execute(teamEntity)
    }

    fun updateTeam(teamEntity: TeamEntity){
        TeamUpdate(teamDao).execute(teamEntity)
    }

    fun deleteTeam(teamEntity: TeamEntity){
        TeamDelete(teamDao).execute(teamEntity)
    }

    fun deleteAllTeam(){
       teamDao.deleteAll()
    }

    fun getTeam(teamId: Int):TeamEntity{
        return teamDao.findByTeamId(teamId)
    }

    fun getAllTeam():List<TeamEntity>{
        return allTeamData
    }

    private class TeamInsert(teamDao: TeamDao): AsyncTask<TeamEntity, Void, Void>(){

        private var teamDao:TeamDao
        init{
            this.teamDao=teamDao
        }

        override fun doInBackground(vararg p0: TeamEntity): Void? {
            teamDao.insert(p0[0])
            return null
        }

    }

    private class TeamUpdate(teamDao: TeamDao): AsyncTask<TeamEntity, Void, Void>(){

        private var teamDao:TeamDao
        init{
            this.teamDao=teamDao
        }

        override fun doInBackground(vararg p0: TeamEntity): Void? {
            teamDao.update(p0[0])
            return null
        }
    }

    private class TeamDelete(teamDao: TeamDao): AsyncTask<TeamEntity, Void, Void>(){

        private var teamDao:TeamDao
        init{
            this.teamDao=teamDao
        }

        override fun doInBackground(vararg p0: TeamEntity): Void? {
            teamDao.delete(p0[0])
            return null
        }
    }

////////////////////////////////TopPlayers ////////////////////////////

    fun insertTopPlayers(topaPlayerEntity: TopPlayerEntity){
        TopPlayersInsert(topPlayersDao).execute(topaPlayerEntity)
    }

    fun updateTopPlayers(topaPlayerEntity: TopPlayerEntity){
        TopPlayersUpdate(topPlayersDao).execute(topaPlayerEntity)
    }

    fun deleteTopPlayers(topaPlayerEntity: TopPlayerEntity){
        TopPlayersDelete(topPlayersDao).execute(topaPlayerEntity)
    }

    fun deleteAllTopPlayers(){
        topPlayersDao.deleteAll()
    }

    fun getTopPlayer(match_id: String,match_type: String,team_id: Int,player_Id:Int):TopPlayerEntity{
        return topPlayersDao.getTopPlayer(match_id,match_type,team_id,player_Id)
    }

    fun getTopPlayers(match_id: String,match_type: String,team_id: Int):List<TopPlayerEntity>{
        return topPlayersDao.getTopPlayers(match_id,match_type,team_id)
    }

    fun getAllTopPlayers():List<TopPlayerEntity>{
        return allTopPlayersData
    }

    private class TopPlayersInsert(topPlayersDao: TopPlayersDao): AsyncTask<TopPlayerEntity, Void, Void>(){

        private var topPlayersDao:TopPlayersDao
        init{
            this.topPlayersDao=topPlayersDao
        }

        override fun doInBackground(vararg p0: TopPlayerEntity): Void? {
            topPlayersDao.insert(p0[0])
            return null
        }

    }

    private class TopPlayersUpdate(topPlayersDao: TopPlayersDao): AsyncTask<TopPlayerEntity, Void, Void>(){

        private var topPlayersDao:TopPlayersDao
        init{
            this.topPlayersDao=topPlayersDao
        }

        override fun doInBackground(vararg p0: TopPlayerEntity): Void? {
            topPlayersDao.update(p0[0])
            return null
        }
    }

    private class TopPlayersDelete(topPlayersDao: TopPlayersDao): AsyncTask<TopPlayerEntity, Void, Void>(){

        private var topPlayersDao:TopPlayersDao
        init{
            this.topPlayersDao=topPlayersDao
        }

        override fun doInBackground(vararg p0: TopPlayerEntity): Void? {
            topPlayersDao.delete(p0[0])
            return null
        }
    }

}




