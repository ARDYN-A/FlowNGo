package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val key2 = "SELECTED_INGREDIENT"
private const val key3 = "SERVINGS"

class Servings : AppCompatActivity(){

    private var selectedIngredient: Int = -1
    private var drinkOrder: DoubleArray = DoubleArray(10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servings)

        selectedIngredient = intent.getIntExtra(key2, -1)
        drinkOrder = intent.getDoubleArrayExtra(key3) as DoubleArray

        val eText = findViewById<EditText>(R.id.numServings) as EditText
        eText.setText(drinkOrder[selectedIngredient].toString())

        val backButton = findViewById<Button>(R.id.backbutton)

        backButton.setOnClickListener {
            drinkOrder[selectedIngredient] = eText.text.toString().toDouble()
            val intent = Intent(this, Ingredients::class.java)
            intent.putExtra(key3, drinkOrder)
            startActivity(intent)
        }
    }
}
