package com.mab.mahusay_add_student

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mab.mahusay_add_student.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()

            val editTextID = findViewById<EditText>(R.id.editTextID)
            val idNum = editTextID.text.toString()
            if (idNum.isEmpty()) {
                // Convert the input to an integer
                Toast.makeText(this, "Please enter ID Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val course = binding.editTextCourse.text.toString()

            val editTextYear = findViewById<EditText>(R.id.editTextYrLvl)
            val year = editTextYear.text.toString()
            if (year.isEmpty()) {
                Toast.makeText(this, "Please enter your year level", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                // Input is not empty, check if it exceeds 5
                val yrLvl = year.toIntOrNull()
                if (yrLvl == null || yrLvl > 5) {
                    // Input is either not a number or greater than 5
                    Toast.makeText(this, "Year level: 1 to 5.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val address = binding.editTextAddress.text.toString()
            val date = binding.editTextDate.text.toString()


            val email = binding.editTextEmailAddress.text.toString()
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                // Display an error message
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit the function if the email is invalid
            }


            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("NAME_KEY", name)
            intent.putExtra("ID_KEY", idNum)
            intent.putExtra("COURSE_KEY", course)
            intent.putExtra("YEAR_KEY", year)
            intent.putExtra("ADDRESS_KEY", address)
            intent.putExtra("DATE_KEY", date)
            intent.putExtra("EMAIL_KEY", email)

//            intent.putExtra("PASSWORD_KEY", password)
            startActivity(intent)
        }
    }
}