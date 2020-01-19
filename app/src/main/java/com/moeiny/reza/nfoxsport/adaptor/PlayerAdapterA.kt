package com.moeiny.reza.nfoxsport.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.database.entitiy.TopPlayerEntity
import com.moeiny.reza.nfoxsport.model.entity.TopPlayer
import com.moeiny.reza.nfoxsport.utils.API
import com.moeiny.reza.nfoxsport.view.ShowActivity
import com.squareup.picasso.Picasso

import java.net.MalformedURLException
import java.net.URL

class PlayerAdapterA(var context: Context, var topPlayerList:List<TopPlayerEntity>): RecyclerView.Adapter<PlayerAdapterA.TopPlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlayerViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.playerrow,parent,false)
        return TopPlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topPlayerList.count()
    }

    override fun onBindViewHolder(holderTopPlayer: TopPlayerViewHolder, position: Int) {
        var topPlayer:TopPlayerEntity=topPlayerList.get(position)

        if (isValid(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",topPlayer.player_Id.toString())))

              Picasso.get().load(API.GET_PLAYERIMAGE_URL.value.replace("@player_Id",topPlayer.player_Id.toString())).into(holderTopPlayer.imgPicUrl)
        else
            Picasso.get().load(API.GET_DEFAULTIMAGE_URL.value.replace("@player_Id",topPlayer.player_Id.toString())).into(holderTopPlayer.imgPicUrl)

        holderTopPlayer.txtname.setText("Name : " + topPlayer.player_shortname)
        holderTopPlayer.txtjumpernumber.setText("Jumper Number : " + topPlayer.player_jumpernumber.toString())
        holderTopPlayer.txtstatvalue.setText("Sate Value : " + topPlayer.player_statvalue.toString())
        holderTopPlayer.txtposition.setText("Position : " + topPlayer.player_position)

//        val sharedPref: SharedPreferences = context!!.getSharedPreferences("Info", Context.MODE_PRIVATE)
//        var teamA_id= sharedPref.getString("teamA_id", null)

        holderTopPlayer.parent.setOnClickListener(){
            val intent = Intent(context, ShowActivity::class.java)
            intent.putExtra("team_Id", topPlayer.team_Id.toString())
            intent.putExtra("player_Id", topPlayer.player_Id.toString())
            context!!.startActivity(intent)
        }
    }

    inner class TopPlayerViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        var txtname= itemView!!.findViewById<TextView>(R.id.txt_playerrow_name)
        var txtjumpernumber= itemView!!.findViewById<TextView>(R.id.txt_playerrow_jumpernumber)
        var txtposition= itemView!!.findViewById<TextView>(R.id.txt_playerrow_position)
        var txtstatvalue= itemView!!.findViewById<TextView>(R.id.txt_playerrow_statvalue)
        var imgPicUrl=itemView!!.findViewById<ImageView>(R.id.img_playerrow_image)
        var parent=itemView!!.findViewById<RelativeLayout>(R.id.rl_playerrow_parent)
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