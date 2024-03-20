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
            if (idNum.isNotEmpty()) {
                // Convert the input to an integer
                val id = idNum.toIntOrNull()

                // Check if the conversion was successful and the number is less than 5
                if (idNum != null) {
                    // Input is a valid number and less than 5
                    // You can proceed with further actions here
                    // For example, display a success message
                    Toast.makeText(this, "Please enter ID Number", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Input is empty
                // Display an error message
                Toast.makeText(this, "Year ID Number cannot be empty", Toast.LENGTH_SHORT).show()
            }

            val course = binding.editTextCourse.text.toString()

            val editTextYear = findViewById<EditText>(R.id.editTextYrLvl)
            val year = editTextYear.text.toString()
            if (year.isNotEmpty()) {
                // Convert the input to an integer
                val yrLvl = year.toIntOrNull()

                // Check if the conversion was successful and the number is less than 5
                if (yrLvl != null && yrLvl <= 5) {
                    // Input is a valid number and less than 5
                    // You can proceed with further actions here
                    // For example, display a success message
                    Toast.makeText(this, "Enter your year level", Toast.LENGTH_SHORT).show()
                } else {
                    // Input is either not a number or greater than 5
                    // Display an error message
                    Toast.makeText(this, "Please enter your year level", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Input is empty
                // Display an error message
                Toast.makeText(this, "Year level cannot be empty", Toast.LENGTH_SHORT).show()
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