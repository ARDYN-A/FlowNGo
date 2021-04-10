package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private const val key2 = "SELECTED_INGREDIENT"
private const val key3 = "SERVINGS"

class Servings : AppCompatActivity(){

    private var selectedIngredient: Int = -1
    private var drinkOrder: DoubleArray = DoubleArray(10)
    private var s: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servings)

        selectedIngredient = intent.getIntExtra(key2, -1)
        drinkOrder = intent.getDoubleArrayExtra(key3) as DoubleArray

        val eText = findViewById<EditText>(R.id.numServings) as EditText
        eText.setText(drinkOrder[selectedIngredient].toString())

        val backButton = findViewById<Button>(R.id.adminbutton)

        backButton.setOnClickListener {
            drinkOrder[selectedIngredient] = eText.text.toString().toDouble()
            if(drinkOrder[selectedIngredient] < 30.0) {
                Toast.makeText(applicationContext, "Minimum 30mL", Toast.LENGTH_SHORT).show()
                drinkOrder[selectedIngredient] = 0.0
            }
            for(i in 0 until 10){
                s += drinkOrder[i];
            }
            if(s > 300.0) {
                eText.setText("0.0")
                drinkOrder[selectedIngredient] = eText.text.toString().toDouble()
                Toast.makeText(applicationContext, "$s/300", Toast.LENGTH_SHORT).show()
                s = 0.0
            } else {
                val intent = Intent(this, Ingredients::class.java)
                intent.putExtra(key3, drinkOrder)
                startActivity(intent)
            }
        }
    }
}
