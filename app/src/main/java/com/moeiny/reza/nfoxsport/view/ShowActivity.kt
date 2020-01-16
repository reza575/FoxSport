package com.moeiny.reza.nfoxsport.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.adapter.StatAdapter
import com.moeiny.reza.nfoxsport.model.entity.StatRow
import com.squareup.picasso.Picasso
import java.net.MalformedURLException
import java.net.URL
import org.json.JSONObject
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.presenter.MatchService
import com.moeiny.reza.nfoxsport.utils.API
import com.moeiny.reza.nfoxsport.utils.FoxSportCallback


class ShowActivity : AppCompatActivity() {
    lateinit var stats: Stats
    lateinit var recyclerView: RecyclerView
    lateinit var txtFullname: TextView
    lateinit var txtPosition: TextView
    lateinit var imgPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setUpView()
        val bundle = intent.extras
        var team_Id = bundle?.getString("team_Id")
        var player_Id=bundle?.getString("player_Id").toString()
        if (isValid(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",player_Id)))

            Picasso.get().load(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",player_Id)).into(imgPhoto)
        else
            Picasso.get().load(API.GET_DEFAULTIMAGE_URL.value.replace("@player_Id",player_Id)).into(imgPhoto)

        getStatInfo(team_Id!!,player_Id)
    }

    fun getStatInfo(team_Id:String,player_Id:String) {

        MatchService.getStatsInfo(team_Id,player_Id,object : FoxSportCallback<Stats, Throwable> {

            override fun onSuccess(result: Stats) {
                stats = result

                loadData()
            }

            override fun onError(error: Throwable?) {
                //      Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                print("complete")
            }

        })
    }

    fun loadData(){
        txtFullname.text = "Full Name : " +stats.full_name
        txtPosition.text = "Position : " + stats.position
        var gson: Gson =  Gson();
        var json:String  = gson.toJson(stats.last_match_stats)

        val obj = JSONObject(json)
        val last_match_stats = JSONObject(gson.toJson(stats.last_match_stats))
        val items = ArrayList<StatRow>()
        val keys = last_match_stats.keys()
        while (keys.hasNext()) {
            val next = keys.next()
            items.add(StatRow(next, last_match_stats.getString(next)))
        }

        setDataOnRecycler(items)
    }

    fun setDataOnRecycler(items:ArrayList<StatRow>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = StatAdapter(this, items)
    }

    fun setUpView() {
        txtFullname = findViewById(R.id.txt_showactivity_fullname)
        txtPosition = findViewById(R.id.txt_showactivity_position)
        imgPhoto = findViewById(R.id.img_showactivity_photo)
        recyclerView = findViewById(R.id.rv_showactivity_stat)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun isValid(urlString: String): Boolean {
        try {
            val url = URL(urlString)
            return URLUtil.isValidUrl(url.toString()) && Patterns.WEB_URL.matcher(url.toString()).matches()
        } catch (e: MalformedURLException) {

        }

        return false
    }


}
