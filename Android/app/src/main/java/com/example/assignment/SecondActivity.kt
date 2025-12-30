package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val back = findViewById<ImageButton>(R.id.imageButton)
        val resultText= findViewById<TextView>(R.id.genderText)



        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val name = intent.getStringExtra("NAME")
        val age = intent.getStringExtra("AGE")
        val gender = intent.getStringExtra("SELECTED_GENDER")


        tvName.text = "Name: $name"
        tvAge.text = "Age: $age"
        resultText.text = "Gender: $gender"
    }
}