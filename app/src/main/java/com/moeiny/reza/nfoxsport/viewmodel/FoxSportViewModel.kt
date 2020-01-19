package com.moeiny.reza.nfoxsport.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.moeiny.reza.nfoxsport.MainActivity
import com.moeiny.reza.nfoxsport.database.entitiy.*
import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.repository.FoxSportRepository
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback
import com.moeiny.reza.nfoxsport.view.HomeActivity
import java.util.concurrent.CountDownLatch


class FoxSportViewModel(application: Application) : AndroidViewModel(application) {

    private  var foxSportRepository: FoxSportRepository

    private  var allMatchData:List<MatchEntity>
    private  var allPlayerData:List<PlayerEntity>
    private  var allStatsData:List<StatsEntity>
    private  var allTeamData:List<TeamEntity>
    private  var allTopPlayerData:List<TopPlayerEntity>




    init {
        foxSportRepository= FoxSportRepository(application)
        allMatchData=foxSportRepository.getAllMatch()
        allPlayerData=foxSportRepository.getAllPlayer()
        allStatsData=foxSportRepository.getAllStats()
        allTeamData=foxSportRepository.getAllTeam()
        allTopPlayerData=foxSportRepository.getAllTopPlayers()

    }

    fun insert(matchEntity: MatchEntity){
        foxSportRepository.insertMatch(matchEntity)
    }

    fun update(matchEntity: MatchEntity){
        foxSportRepository.updateMatch(matchEntity)
    }

    fun delete(matchEntity: MatchEntity){
        foxSportRepository.deleteMatch(matchEntity)
    }

    fun deleteAllMatch(){
        foxSportRepository.deleteAllMatch()
    }

    fun getmatch(matchId: String,stat_type:String):MatchEntity{
        return foxSportRepository.getMatch(matchId,stat_type)
    }

    fun getmatchbyType(matchtype: String):MatchEntity{
        return foxSportRepository.getMatchbyType(matchtype )
    }

    fun getAllMatch():List<MatchEntity>{
        return allMatchData
    }


    ///////////////////////Player Entity////////////////////////////

    fun insert(playerEntity: PlayerEntity){
        foxSportRepository.insertPlayer(playerEntity)
    }

    fun update(playerEntity: PlayerEntity){
        foxSportRepository.updatePlayer(playerEntity)
    }

    fun delete(playerEntity: PlayerEntity){
        foxSportRepository.deletePlayer(playerEntity)
    }

    fun deleteAllPlayer(){
        foxSportRepository.deleteAllPlayer()
    }

    fun getPlayer(playerId: Int,team_Id:Int): PlayerEntity{
        return foxSportRepository.getPlayer(playerId,team_Id)
    }

    fun getPlayerbyTeam(team_id: Int):List<PlayerEntity>{
        return foxSportRepository.getPlayerbyTeam(team_id)
    }

    fun getAllPlayer(): List<PlayerEntity>{
        return allPlayerData
    }

    //////////////////////////Stats Entity/////////////////////////

    fun insert(statsEntity: StatsEntity){
        foxSportRepository.insertStats(statsEntity)
    }

    fun update(statsEntity: StatsEntity){
        foxSportRepository.updateStats(statsEntity)
    }

    fun delete(statsEntity: StatsEntity){
        foxSportRepository.deleteStats(statsEntity)
    }

    fun deleteAllStats(){
        foxSportRepository.deleteAllStats()
    }

    fun getStat(player_id: Int,team_Id:Int):StatsEntity{
        return foxSportRepository.getStat(player_id,team_Id)
    }

    fun getAllStats():List<StatsEntity>{
        return allStatsData
    }

    /////////////////////////Team Entity//////////////////////////

    fun insert(teamEntity: TeamEntity){
        foxSportRepository.insertTeam(teamEntity)
    }

    fun update(teamEntity: TeamEntity){
        foxSportRepository.updateTeam(teamEntity)
    }

    fun delete(teamEntity: TeamEntity){
        foxSportRepository.deleteTeam(teamEntity)
    }

    fun deleteAllTeam(){
        foxSportRepository.deleteAllTeam()
    }

    fun getTeam(team_Id: Int):TeamEntity{
        return foxSportRepository.getTeam(team_Id)
    }

    fun getAllTeam():List<TeamEntity>{
        return allTeamData
    }

    ////////////////////////TopPlayers Entity///////////////////////////

    fun insert(topPlayerEntity: TopPlayerEntity){
        foxSportRepository.insertTopPlayers(topPlayerEntity)
    }

    fun update(topPlayerEntity: TopPlayerEntity){
        foxSportRepository.updateTopPlayers(topPlayerEntity)
    }

    fun delete(topPlayerEntity: TopPlayerEntity){
        foxSportRepository.deleteTopPlayers(topPlayerEntity)
    }

    fun deleteAllTopPlayers(){
        foxSportRepository.deleteAllTopPlayers()
    }

    fun getTopPlayer(match_id: String,match_type: String,team_id: Int,player_Id:Int):TopPlayerEntity{
        return foxSportRepository.getTopPlayer(match_id,match_type,team_id,player_Id)
    }

    fun getTopPlayers(match_id: String,match_type: String,team_id: Int):List<TopPlayerEntity>{
        return foxSportRepository.getTopPlayers(match_id,match_type,team_id)
    }

    fun getAllTopPlayers():List<TopPlayerEntity>{
        return allTopPlayerData
    }


/////////////////////////////////////////////////////////////

fun getMatchInfo(){

       lateinit var matchList:ArrayList<Match>
       val lock =  CountDownLatch(1)


    MatchService.getMatchesInfo(object : FoxSportCallback<List<Match>, Throwable> {

            override fun onSuccess(result: List<Match>) {
                lock.countDown()
                matchList = ArrayList<Match>()
                matchList = result as ArrayList<Match>
                for(i in 0..matchList.size-1){
                    var match=MatchEntity(matchList[i].match_id,matchList[i].team_A.id,matchList[i].team_B.id,matchList[i].stat_type)
                    if(getmatch(matchList[i].match_id,matchList[i].stat_type)==null)
                          insert(match)

                    for(j in 0..matchList[i].team_A.top_players.size-1){
                        var topPlayer=TopPlayerEntity(matchList[i].match_id,matchList[i].stat_type,
                            matchList[i].team_A.id,matchList[i].team_A.top_players[j].id,matchList[i].team_A.top_players[j].full_name,
                            matchList[i].team_A.top_players[j].jumper_number,matchList[i].team_A.top_players[j].short_name,
                            matchList[i].team_A.top_players[j].position,matchList[i].team_A.top_players[j].stat_value)

                        if(getTopPlayer(matchList[i].match_id,matchList[i].stat_type,
                                        matchList[i].team_A.id,matchList[i].team_A.top_players[j].id)==null)
                             insert(topPlayer)

                        getStatInfo(matchList[i].team_A.id.toString(),matchList[i].team_A.top_players[j].id.toString())
                    }

                    for(j in 0..matchList[i].team_B.top_players.size-1){
                        var topPlayer=TopPlayerEntity(matchList[i].match_id,matchList[i].stat_type,
                            matchList[i].team_B.id,matchList[i].team_B.top_players[j].id,matchList[i].team_B.top_players[j].full_name,
                            matchList[i].team_B.top_players[j].jumper_number,matchList[i].team_B.top_players[j].short_name,
                            matchList[i].team_B.top_players[j].position,matchList[i].team_B.top_players[j].stat_value)
                        if(getTopPlayer(matchList[i].match_id,matchList[i].stat_type,
                                        matchList[i].team_B.id,matchList[i].team_B.top_players[j].id)==null)
                                insert(topPlayer)

                        getStatInfo(matchList[i].team_B.id.toString(),matchList[i].team_B.top_players[j].id.toString())
                    }
                }

                var teamA=TeamEntity(matchList[0].team_A.id,matchList[0].team_A.name,matchList[0].team_A.code,matchList[0].team_A.short_name)
                var teamB=TeamEntity(matchList[0].team_B.id,matchList[0].team_B.name,matchList[0].team_B.code,matchList[0].team_B.short_name)
                if(getTeam(matchList[0].team_A.id)==null)
                    insert(teamA)
                if(getTeam(matchList[0].team_B.id)==null)
                    insert(teamB)

            }

            override fun onError(error: Throwable?) {
                lock.countDown()
                //      Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                lock.countDown()
                print("complete")
                var view= HomeActivity.getView()
                view.visibility= View.INVISIBLE
                HomeActivity.move()

            }

        })
    }


    ///////////////////////////////

    fun getStatInfo(team_Id:String,player_Id:String) {

        val lock =  CountDownLatch(1)
        lateinit var stats: Stats


        MatchService.getStatsInfo(team_Id,player_Id,object : FoxSportCallback<Stats, Throwable> {

            override fun onSuccess(result: Stats) {
                lock.countDown()
                stats = result

                var stat=StatsEntity(stats.id,team_Id.toInt(),stats.surname,stats.position,stats.full_name,stats.short_name,
                    stats.date_of_birth,stats.height_cm,stats.other_names,stats.weight_kg,stats.last_match_id,
                    stats.career_stats.toString(),Gson().toJson(stats.last_match_stats),stats.series_season_stats.toString())

                if(getStat(stats.id,team_Id.toInt())==null)
                    insert(stat)
                var str=Gson()

            }

            override fun onError(error: Throwable?) {
                lock.countDown()
                //      Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                lock.countDown()
                print("complete")
            }

        })
    }

 }