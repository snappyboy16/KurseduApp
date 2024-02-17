package com.example.kursedapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            intentAuth()
        }, 3000)
    }
    fun intentAuth() {
        val intentActivityAuth = Intent(this, Auth::class.java)
        startActivity(intentActivityAuth)
        finish()
    }
}
