package com.example.kursedapp


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CourseActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var database: DatabaseReference
    val professions = arrayOf("\uD83D\uDCBC Программирование", "\uD83D\uDCBC Дизайн", "\uD83D\uDCBC Маркетолог")
    val courses = arrayOf("")
    val format = arrayOf("")
    val platform = arrayOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        ProfessionSimpleSpinner()
        bottomNavigationView = findViewById(R.id.nav_view)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.ic_add -> {
                    intentAdd()
                    // replaceFragment(AddFragment())
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
    private fun ProfessionSimpleSpinner() {
        val spinner: Spinner = findViewById(R.id.ProfessionSpinner)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, getProfessionsSpinner()).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.prompt = "Профессия"
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                Toast.makeText(this@CourseActivity, "$selectedItem выбрано!", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun CourseSimpleSpinner() {
        val spinner: Spinner = findViewById(R.id.CourseSpinner)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, professions).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                Toast.makeText(this@CourseActivity, "$selectedItem выбрано!", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun FormatSimpleSpinner() {
        val spinner: Spinner = findViewById(R.id.FormatSpinner)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, professions).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.prompt = "Формат"
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                Toast.makeText(this@CourseActivity, "$selectedItem выбрано!", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun PlatformSimpleSpinner() {
        val spinner: Spinner = findViewById(R.id.PlatformSpinner)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, professions).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                Toast.makeText(this@CourseActivity, "$selectedItem выбрано!", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun getProfessionsSpinner(): Array<Task<DataSnapshot>> {
        database = FirebaseDatabase.getInstance().getReference("Professions")

        return arrayOf()
    }
    private fun intentProfile() {
        val intentProfileActivity = Intent(this, ProfileActivity::class.java)
        startActivity(intentProfileActivity)
        finish()
    }
    private fun intentAdd() {
        val intentAddActivity = Intent(this, AddActivity::class.java)
        startActivity(intentAddActivity)
        finish()
    }
}