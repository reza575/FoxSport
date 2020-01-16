package com.moeiny.reza.nfoxsport

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.nfoxsport.adapter.PlayerAdapterA
import com.moeiny.reza.nfoxsport.adapter.PlayerAdapterB
import com.moeiny.reza.nfoxsport.model.entity.Match
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback


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
    // lateinit var viewModel: FoxSportViewModel

    lateinit var spiner: Spinner
    var index=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        //   viewModel.getMathInfo()
        // viewModel.getStatInfo()
        getMatchInfo()
    }

    fun getMatchInfo() {

        MatchService.getMatchesInfo(object : FoxSportCallback<List<Match>, Throwable> {

            override fun onSuccess(result: List<Match>) {
                matchList = ArrayList<Match>()
                matchList = result as ArrayList<Match>
                fillSpinner()

                loadData(0)
            }

            override fun onError(error: Throwable?) {
                //      Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                print("complete")
            }

        })
    }

    fun loadData(index:Int){
        txtTeamAname.text = "Name : " + matchList[index].team_A.name
        txtTeamAcode.text = "Code : " + matchList[index].team_A.code
        txtTeamAshortname.text = "Short Name : " + matchList[index].team_A.short_name
        txtTeamBname.text = "Name : " + matchList[index].team_B.name
        txtTeamBcode.text = "Code : " + matchList[index].team_B.code
        txtTeamBshortname.text = "Short Name : " + matchList[index].team_B.short_name


        setDataOnRecycler(index)

    }

    fun fillSpinner(){
        var statArray=ArrayList<String>()
        for(i in 0..matchList.size-1)
        {
            statArray.add(matchList[i].stat_type)
        }

        val adp1 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statArray)
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.setAdapter(adp1)

        val sharedPref: SharedPreferences = this!!.getSharedPreferences("Info", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("teamA_id", matchList[0].team_A.id.toString())
        editor!!.commit()

        editor.putString("teamB_id", matchList[0].team_B.id.toString())
        editor!!.commit()
    }

    fun setDataOnRecycler(index:Int) {
        recyclerViewA.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewA.adapter = PlayerAdapterA(this, matchList[index].team_A.top_players)

        recyclerViewB.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewB.adapter = PlayerAdapterB(this, matchList[index].team_B.top_players)
    }

    fun setUpView() {
        //     viewModel = ViewModelProviders.of(this).get(FoxSportViewModel::class.java)
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
                for(i in 0..matchList.size-1)
                {
                    if (matchList[i].stat_type.equals(s)){
                        index=i
                    }

                }
                loadData(index)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        })
    }
}
