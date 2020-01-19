package com.moeiny.reza.nfoxsport.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.nfoxsport.MainActivity
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.model.entity.Stats
import com.moeiny.reza.nfoxsport.viewmodel.FoxSportViewModel


class HomeActivity : AppCompatActivity() {
    lateinit var stats: Stats
    lateinit var viewModel: FoxSportViewModel


    lateinit var txt_Continue: TextView
    lateinit var rl_loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setContext(this)
        viewModel = ViewModelProviders.of(this).get(FoxSportViewModel::class.java)


        txt_Continue = findViewById(R.id.txt_home_continue)
        rl_loading = findViewById(R.id.loadingPanel)

        txt_Continue.setOnClickListener {
            setView(rl_loading)
            txt_Continue.text = "Loading..."
            rl_loading.visibility= View.VISIBLE
            viewModel.getMatchInfo()
        }

    }

    companion object {
        private lateinit var context:Context

        private lateinit var view: ProgressBar

        fun getView(): ProgressBar { return this.view}
        fun setView(view : ProgressBar) { this.view=view }

        fun setContext(context:Context){
           this.context=context
        }

        fun move(){
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }






}
