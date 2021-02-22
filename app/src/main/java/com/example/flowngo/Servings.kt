package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val skey = "POSITION"

class Servings : AppCompatActivity(){
    private var spos = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servings)

        spos = intent.getIntExtra(skey, -1)
        val nextButton = findViewById<Button>(R.id.nextbutton)
        val backButton = findViewById<Button>(R.id.backbutton)
        nextButton.setOnClickListener {
            val intent = Intent(this, Confirmed::class.java)
            //intent.putExtra(key, pos)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, Ingredients::class.java)
            intent.putExtra(skey, spos)
            startActivity(intent)
        }
    }
}
