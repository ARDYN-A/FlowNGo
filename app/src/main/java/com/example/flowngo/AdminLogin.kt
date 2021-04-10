package com.example.flowngo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        val username = findViewById<EditText>(R.id.usern) as EditText
        val password= findViewById<EditText>(R.id.passw) as EditText

        val submitButton = findViewById<Button>(R.id.button)

        submitButton.setOnClickListener {
            if(username.text.toString() == "username" && password.text.toString() == "password") {
                val intent = Intent(this, Admin::class.java)
                Toast.makeText(applicationContext, "Success!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }
    }
}