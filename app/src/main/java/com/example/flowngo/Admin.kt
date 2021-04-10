package com.example.flowngo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Admin : AppCompatActivity() {
    private val db = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val clearButton = findViewById<Button>(R.id.clearbutton)
        val returnButton = findViewById<Button>(R.id.returnbutton)
        clearButton.setOnClickListener {
            db.getReference("Amount0").setValue(0)
            db.getReference("Amount1").setValue(0)
            db.getReference("Amount2").setValue(0)
            db.getReference("Amount3").setValue(0)
            db.getReference("Amount4").setValue(0)
            db.getReference("Amount5").setValue(0)
            db.getReference("Amount6").setValue(0)
            db.getReference("Amount7").setValue(0)
            db.getReference("Amount8").setValue(0)
            db.getReference("Amount9").setValue(0)
            db.getReference("Count").setValue(0)
        }
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}