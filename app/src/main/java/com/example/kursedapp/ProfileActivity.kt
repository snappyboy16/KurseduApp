package com.example.kursedapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ProfileActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.ic_course -> {
                    intentCourse()
                    // replaceFragment(CourseFragment())
                    true
                }
                R.id.ic_add -> {
                    intentAdd()
                    // replaceFragment(AddFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(CourseFragment())
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
    fun exitAccount(view: View) {
        Firebase.auth.signOut()
        val intentAuthActivity = Intent(this, AuthActivity::class.java)
        startActivity(intentAuthActivity)
        finish()
    }
    private fun intentCourse() {
        val intentCourseActivity = Intent(this, CourseActivity::class.java)
        startActivity(intentCourseActivity)
        finish()
    }
    private fun intentAdd() {
        val intentAddActivity = Intent(this, AddActivity::class.java)
        startActivity(intentAddActivity)
        finish()
    }
}