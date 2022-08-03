package com.miempresa.rapido2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_auxiliar.*
import kotlinx.android.synthetic.main.app_bar_main.*

class Auxiliar : AppCompatActivity() {
    lateinit var  toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auxiliar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}