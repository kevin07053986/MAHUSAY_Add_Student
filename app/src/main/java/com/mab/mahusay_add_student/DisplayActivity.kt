package com.mab.mahusay_add_student

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class DisplayActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val id = intent.getStringExtra("ID_KEY")
        val lname = intent.getStringExtra("LNAME_KEY")
        val fname = intent.getStringExtra("FNAME_KEY")
        val phoneNum = intent.getStringExtra("PHONE_KEY")
        val photoUrl = intent.getStringExtra("PHOTO_KEY")

        imageView = findViewById<ImageView>(R.id.dispImage)
        Picasso.get().load(photoUrl).into(imageView)

        findViewById<TextView>(R.id.dispTextID).text = id
        findViewById<TextView>(R.id.dispTextName).text = lname + ", " + fname
        findViewById<Button>(R.id.buttonTextNum).text = phoneNum

        val phoneNumTextView = findViewById<TextView>(R.id.buttonTextNum)
        phoneNumTextView.text = phoneNum
        phoneNumTextView.setOnClickListener {
            val phoneNumber = phoneNumTextView.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(callIntent)
            }
        }
//        val address = intent.getStringExtra("ADDRESS_KEY")
//        val date = intent.getStringExtra("DATE_KEY")
//        val email = intent.getStringExtra("EMAIL_KEY")


//        findViewById<TextView>(R.id.dispTextAddress).text = address
//        findViewById<TextView>(R.id.dispTextDate).text = date
//        findViewById<TextView>(R.id.dispTextEmailAddress).text = email

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            // Create an intent to start the AddActivity
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }
}