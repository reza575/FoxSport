package com.moeiny.reza.nfoxsport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.nfoxsport.adapter.PlayerAdapterA
import com.moeiny.reza.nfoxsport.adapter.PlayerAdapterB
import com.moeiny.reza.nfoxsport.database.entitiy.MatchEntity
import com.moeiny.reza.nfoxsport.database.entitiy.StatsEntity
import com.moeiny.reza.nfoxsport.database.entitiy.TeamEntity
import com.moeiny.reza.nfoxsport.database.entitiy.TopPlayerEntity
import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback
import com.moeiny.reza.nfoxsport.viewmodel.FoxSportViewModel
import java.util.concurrent.CountDownLatch


class MainActivity : AppCompatActivity() {
    lateinit var matchList:ArrayList<Match>
    lateinit var recyclerViewA: RecyclerView
    lateinit var recyclerViewB: RecyclerView
    lateinit var txtTeamAname: TextView
    lateinit var txtTeamAcode: TextView
    lateinit var txtTeamAshortname: TextView
    lateinit var txtTeamBname: TextView
    lateinit var txtTeamBcode: TextView
    lateinit var txtTeamBshortname: TextView
    lateinit var viewModel: FoxSportViewModel
    lateinit var matches:List<MatchEntity>
    lateinit var spiner: Spinner
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        matchList = ArrayList<Match>()
        setUpView()
        matches=viewModel.getAllMatch()
        fillSpinner()
        loadData(0)
    }

    fun loadData(index:Int){
        var teamA=viewModel.getTeam(matches[index].teamA_Id)
        var teamB=viewModel.getTeam(matches[index].teamB_Id)
        txtTeamAname.text = "Name : " + teamA.team_name
        txtTeamAcode.text = "Code : " + teamA.team_code
        txtTeamAshortname.text = "Short Name : " + teamA.team_shortname
        txtTeamBname.text = "Name : " +teamB.team_name
        txtTeamBcode.text = "Code : " + teamB.team_code
        txtTeamBshortname.text = "Short Name : " + teamB.team_shortname

        setDataOnRecycler(index)
    }

    fun fillSpinner(){

        var statArray=ArrayList<String>()
        for(i in 0..matches.size-1)
        {
            statArray.add(matches[i].stat_type)
        }

        val adp1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statArray)
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.setAdapter(adp1)
    }

    fun setDataOnRecycler(index:Int) {
        var teamA_topplayers=viewModel.getTopPlayers(matches[index].match_Id,matches[index].stat_type,matches[index].teamA_Id)
        var teamB_topplayers=viewModel.getTopPlayers(matches[index].match_Id,matches[index].stat_type,matches[index].teamB_Id)

        recyclerViewA.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewA.adapter = PlayerAdapterA(this,teamA_topplayers)

        recyclerViewB.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewB.adapter = PlayerAdapterA(this, teamB_topplayers)
    }

    fun setUpView() {
        viewModel = ViewModelProviders.of(this).get(FoxSportViewModel::class.java)
        txtTeamAname = findViewById(R.id.txt_main_teamA_name)
        txtTeamAcode = findViewById(R.id.txt_main_teamA_code)
        txtTeamAshortname = findViewById(R.id.txt_main_teamA_shortname)
        txtTeamBname = findViewById(R.id.txt_main_teamB_name)
        txtTeamBcode = findViewById(R.id.txt_main_teamB_code)
        txtTeamBshortname = findViewById(R.id.txt_main_teamB_shortname)
        spiner = findViewById(R.id.spn_main_statype)
        recyclerViewA = findViewById(R.id.rv_stat_left)
        recyclerViewA.layoutManager = LinearLayoutManager(this)
        recyclerViewB = findViewById(R.id.rv_stat_right)
        recyclerViewB.layoutManager = LinearLayoutManager(this)

        spiner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {

                var s= spiner.selectedItem.toString()
                for(i in 0..matches.size-1)
                {
                    if (matches[i].stat_type.equals(s)){
                        index=i
                    }
                }
                loadData(index)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }
        })
    }

}
