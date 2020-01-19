package com.moeiny.reza.nfoxsport.view

import android.os.Bundle
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.adapter.StatAdapter
import com.moeiny.reza.nfoxsport.model.entity.StatRow
import com.moeiny.reza.nfoxsport.utils.API
import com.moeiny.reza.nfoxsport.viewmodel.FoxSportViewModel
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL


class ShowActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var txtFullname: TextView
    lateinit var txtPosition: TextView
    lateinit var imgPhoto: ImageView
    lateinit var viewModel: FoxSportViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setUpView()
        val bundle = intent.extras
        var team_Id = bundle?.getString("team_Id").toString()
        var player_Id=bundle?.getString("player_Id").toString()
        if (isValid(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",player_Id)))

            Picasso.get().load(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",player_Id)).into(imgPhoto)
        else
            Picasso.get().load(API.GET_DEFAULTIMAGE_URL.value.replace("@player_Id",player_Id)).into(imgPhoto)

        loadData(team_Id!!,player_Id)
    }


    fun loadData(team_Id:String,player_Id:String){

        var stat=viewModel.getStat(player_Id.toInt(),team_Id.toInt())
        txtFullname.text = "Full Name : " +stat.full_name
        txtPosition.text = "Position : " + stat.position

        var json=stat.last_match_stats
        val last_match_stats = JSONObject(json)
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
        viewModel = ViewModelProviders.of(this).get(FoxSportViewModel::class.java)
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
