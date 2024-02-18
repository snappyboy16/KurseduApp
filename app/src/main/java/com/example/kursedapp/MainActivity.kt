package com.example.kursedapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        if (auth.currentUser != null){
            Handler().postDelayed({
                Log.d("Account", "${auth.currentUser!!.email}")
                intentCourse()
            }, 3000)
        } else {
            Handler().postDelayed({
                intentAuth()
            }, 3000)
        }
    }
    fun intentAuth() {
        val intentActivityAuthActivity = Intent(this, AuthActivity::class.java)
        startActivity(intentActivityAuthActivity)
        finish()
    }
    private fun intentCourse() {
        val intentActivityCourseActivity = Intent(this, CourseActivity::class.java)
        startActivity(intentActivityCourseActivity)
        finish()
    }
}
