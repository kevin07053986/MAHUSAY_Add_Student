package com.mab.mahusay_add_student

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mab.mahusay_add_student.databinding.ActivityAddBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {

            val editTextID = findViewById<EditText>(R.id.editTextID)
            val idNum = editTextID.text.toString()
            if (idNum.isEmpty()) {
                // Convert the input to an integer
                Toast.makeText(this, "Please enter ID Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val lname = binding.editTextLName.text.toString()
            val fname = binding.editTextFName.text.toString()
            val phoneNum = binding.editTextPNum.text.toString()
            val photo = binding.editTextPhoto.text.toString()

            saveStudentDataToSharedPreferences()

            // Save the student data to SharedPreferences
            val sharedPreferences = getSharedPreferences("student_data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("ID_KEY", idNum)
            editor.putString("LNAME_KEY", lname)
            editor.putString("FNAME_KEY", fname)
            editor.putString("PHONE_KEY", phoneNum)
            editor.putString("PHOTO_KEY", photo)
            editor.apply()

            // Redirect to ListActivity
            val intent = Intent(this, ListActivity::class.java)

            startActivity(Intent(this, ListActivity::class.java))
        }
    }
    private fun saveStudentDataToSharedPreferences() {

        val idNum = binding.editTextID.text.toString()
        val lname = binding.editTextLName.text.toString()
        val fname = binding.editTextFName.text.toString()
        val phoneNum = binding.editTextPNum.text.toString()
        val photo = binding.editTextPhoto.text.toString()

        // Retrieve existing students from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("students", MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreferences.getString("students_list", null)
        val type = object : TypeToken<ArrayList<Student>>() {}.type
        val students: ArrayList<Student> = gson.fromJson(json, type) ?: ArrayList()

        // Add new student to the list
        students.add(Student(idNum, lname, fname, phoneNum, photo))

        // Save updated student list back to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("students_list", gson.toJson(students))
        editor.apply()
    }


}
