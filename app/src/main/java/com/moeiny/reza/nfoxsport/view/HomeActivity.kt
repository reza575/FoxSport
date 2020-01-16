package com.moeiny.reza.nfoxsport.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*
import com.moeiny.reza.nfoxsport.R
import com.moeiny.reza.nfoxsport.model.entity.Stats


class HomeActivity : AppCompatActivity() {
    lateinit var stats: Stats

    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn = findViewById(R.id.txt_showactivity_fullname)

    }






}
