package com.moeiny.reza.nfoxsport.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.model.entity.StatRow

class StatAdapter(var context: Context, var statList:ArrayList<StatRow>): RecyclerView.Adapter<StatAdapter.StatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.statrow,parent,false)
        return StatsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statList.count()
    }

    override fun onBindViewHolder(holderStats: StatsViewHolder, position: Int) {
        var statRow=statList.get(position)


        holderStats.txtname.setText(statRow.id)
        holderStats.txtvalue.setText(statRow.value)


        holderStats.parent.setOnClickListener(){

        }
    }

    inner class StatsViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        var txtname= itemView!!.findViewById<TextView>(R.id.txt_statrow_name)
        var txtvalue= itemView!!.findViewById<TextView>(R.id.txt_statrow_value)
        var imgline=itemView!!.findViewById<ImageView>(R.id.img_statrow_line)
        var parent=itemView!!.findViewById<RelativeLayout>(R.id.rl_statrow_parent)
    }


}