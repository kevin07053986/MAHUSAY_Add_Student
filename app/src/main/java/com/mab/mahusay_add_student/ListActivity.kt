package com.mab.mahusay_add_student

import android.content.SharedPreferences
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListActivity : AppCompatActivity() {
    private lateinit var studentAdapter: ArrayAdapter<String>
    private lateinit var studentListView: ListView
    private lateinit var students: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        studentListView = findViewById(R.id.listView)

        students = ArrayList()
        studentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        studentListView.adapter = studentAdapter

        retrieveStudentsFromSharedPreferences()

//      Retrieve all saved student data from SharedPreferences
        val sharedPreferences = getSharedPreferences("student_data", MODE_PRIVATE)
        val idNum = sharedPreferences.getString("ID_KEY", "")
        val lname = sharedPreferences.getString("LNAME_KEY", "")
        val fname = sharedPreferences.getString("FNAME_KEY", "")
        val phoneNum = sharedPreferences.getString("PHONE_KEY", "")
//        val photoUrl = sharedPreferences.getString("PHOTO_KEY", "")



        studentListView.setOnItemClickListener { _, _, position, _ ->
            // Retrieve the clicked student from the list
            val clickedStudent = students?.get(position)

            // Create an intent to start the DisplayActivity
            val intent = Intent(this, DisplayActivity::class.java).apply {
                putExtra("ID_KEY", clickedStudent?.idNum)
                putExtra("LNAME_KEY", clickedStudent?.lname)
                putExtra("FNAME_KEY", clickedStudent?.fname)
                putExtra("PHONE_KEY", clickedStudent?.phoneNum)
//                putExtra("PHOTO_KEY", clickedStudent?.photoUrl)
                // Add more data here if needed
            }
            startActivity(intent)
        }

        studentListView.setOnItemLongClickListener { parent, view, position, id ->
            // Get the long-pressed student from the list
            val longPressedStudent = students[position]

            // Remove the long-pressed student from the list
            students.removeAt(position)

            // Update the list view after removing the item
            updateListView()

            // Save the updated list of students to SharedPreferences
            saveStudentsToSharedPreferences()

            // Return true to indicate that the long press event is consumed
            true
        }

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            // Create an intent to start the AddActivity
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val buttonHome = findViewById<Button>(R.id.buttonHome)
        buttonHome.setOnClickListener {
            // Create an intent to start the AddActivity
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
    private fun retrieveStudentsFromSharedPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("students", MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPreferences.getString("students_list", null)
        val type = object : TypeToken<ArrayList<Student>>() {}.type
        students = gson.fromJson(json, type) ?: ArrayList()

        updateListView()
    }

    private fun saveStudentsToSharedPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("students", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(students)
        editor.putString("students_list", json)
        editor.apply()
    }

    private fun updateListView() {
        // Clear existing items
        studentAdapter.clear()

        // Add all students to the adapter
        for (student in students) {
            studentAdapter.add("${student.lname}, ${student.fname}")
        }

        // Notify adapter that the dataset has changed
        studentAdapter.notifyDataSetChanged()
    }
}
