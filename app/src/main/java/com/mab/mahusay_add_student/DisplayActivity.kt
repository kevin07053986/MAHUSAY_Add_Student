package com.mab.mahusay_add_student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val name = intent.getStringExtra("NAME_KEY")
        val id = intent.getStringExtra("ID_KEY")
        val course = intent.getStringExtra("COURSE_KEY")
        val year =intent.getStringExtra("YEAR_KEY")
        val address = intent.getStringExtra("ADDRESS_KEY")
        val date = intent.getStringExtra("DATE_KEY")
        val email = intent.getStringExtra("EMAIL_KEY")

        findViewById<TextView>(R.id.dispTextName).text = name
        findViewById<TextView>(R.id.dispTextID).text = id
        findViewById<TextView>(R.id.dispTextCourse).text = course
        findViewById<TextView>(R.id.dispTextYrLvl).text = year
        findViewById<TextView>(R.id.dispTextAddress).text = address
        findViewById<TextView>(R.id.dispTextDate).text = date
        findViewById<TextView>(R.id.dispTextEmailAddress).text = email

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            // Create an intent to start the AddActivity
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }
}