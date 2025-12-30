package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity() {
    private var isVerified = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnVerify = findViewById<ImageButton>(R.id.btnVerify)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val radioGender = findViewById<RadioGroup>(R.id.gender)
        val male = findViewById<RadioButton>(R.id.radioButton)

        fun resetVerification() {
            isVerified = false
            tvStatus.text = "Not Verified"
            tvStatus.setTextColor(
                resources.getColor(android.R.color.holo_red_dark)
            )
        }
        etName.addTextChangedListener { resetVerification() }
        etAge.addTextChangedListener { resetVerification() }

        btnVerify.setOnClickListener {
            val name = etName.text.toString().trim()
            val ageText = etAge.text.toString().trim()
            if(name.isEmpty()){
                etName.error = "Name required"
                return@setOnClickListener
            }
            if(ageText.isEmpty()){
                etAge.error = "Age required"
                return@setOnClickListener
            }
            val age = ageText.toInt()
            if(age<1 || age>120){
                etAge.error = "Enter valid age"
                return@setOnClickListener
            }

            if (radioGender.checkedRadioButtonId == -1) {
                male.error ="gender required"
                return@setOnClickListener
            }else {
                male.error = null
            }



            isVerified = true
            tvStatus.text = "Verified"
            tvStatus.setTextColor(resources.getColor(android.R.color.holo_green_dark))
        }
        val btnContinue = findViewById<ImageButton>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            if(!isVerified){
                tvStatus.text = "Please verify first"
                tvStatus.setTextColor(
                    resources.getColor(android.R.color.holo_red_dark)
                )
                return@setOnClickListener
            }

            val selectedId = radioGender.checkedRadioButtonId
            val selectedRadio = findViewById<RadioButton>(selectedId)
            val selectedText = selectedRadio.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("NAME", etName.text.toString().trim())
            intent.putExtra("AGE", etAge.text.toString().trim())
            intent.putExtra("SELECTED_GENDER", selectedText)
            startActivity(intent)
        }
    }
}