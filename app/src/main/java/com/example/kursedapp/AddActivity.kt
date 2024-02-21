package com.example.kursedapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.ic_course -> {
                    intentCourse()
                    // replaceFragment(CourseFragment())
                    true
                }
                R.id.ic_account -> {
                    intentProfile()
                    // replaceFragment(ProfileFragment())
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
    private fun intentProfile() {
        val intentProfileActivity = Intent(this, ProfileActivity::class.java)
        startActivity(intentProfileActivity)
        finish()
    }
    private fun intentCourse() {
        val intentCourseActivity = Intent(this, CourseActivity::class.java)
        startActivity(intentCourseActivity)
        finish()
    }
}