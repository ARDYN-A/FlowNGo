package com.example.flowngo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
private const val key3 = "SERVINGS"
private const val key4 = "COUNT"
class dowhateveryouwant : AppCompatActivity() {
    private var count: Long = 0
    private var j: Int = 0;
    private var drinkOrder: DoubleArray = DoubleArray(10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dowhateveryouwant)

        count = intent.getLongExtra(key4, -1)
        if(intent.getDoubleArrayExtra(key3) != null) {
            drinkOrder = intent.getDoubleArrayExtra(key3) as DoubleArray
            val display = listOf("Vodka","Tequila","Gin","Whiskey","Rum","Orange Juice","Triple Sec","Lime Juice","Tonic Water","Coca Cola")
            var ingredient1 = findViewById<TextView>(R.id.ing1)
            var ingredient2 = findViewById<TextView>(R.id.ing2)
            var ingredient3 = findViewById<TextView>(R.id.ing3)
            var ingredient4 = findViewById<TextView>(R.id.ing4)
            var ingredient5 = findViewById<TextView>(R.id.ing5)
            var ingredient6 = findViewById<TextView>(R.id.ing6)
            var ingredient7 = findViewById<TextView>(R.id.ing7)
            var ingredient8 = findViewById<TextView>(R.id.ing8)
            var ingredient9 = findViewById<TextView>(R.id.ing9)
            var ingredient10 = findViewById<TextView>(R.id.ing10)
            var amount1 = findViewById<TextView>(R.id.amt1)
            var amount2 = findViewById<TextView>(R.id.amt2)
            var amount3 = findViewById<TextView>(R.id.amt3)
            var amount4 = findViewById<TextView>(R.id.amt4)
            var amount5 = findViewById<TextView>(R.id.amt5)
            var amount6 = findViewById<TextView>(R.id.amt6)
            var amount7 = findViewById<TextView>(R.id.amt7)
            var amount8 = findViewById<TextView>(R.id.amt8)
            var amount9 = findViewById<TextView>(R.id.amt9)
            var amount10 = findViewById<TextView>(R.id.amt10)
            for(i in 0 until 10){
                if(drinkOrder[i] != 0.0){
                    //set up strings here
                    when (j){
                        0 ->{
                            ingredient1.setText(display[i])
                            amount1.setText(drinkOrder[i].toString() + " mL")
                            ingredient1.setVisibility(View.VISIBLE)
                            amount1.setVisibility(View.VISIBLE)
                        }
                        1 ->{
                            ingredient2.setText(display[i])
                            amount2.setText(drinkOrder[i].toString() + " mL")
                            ingredient2.setVisibility(View.VISIBLE)
                            amount2.setVisibility(View.VISIBLE)
                        }
                        2 ->{
                            ingredient3.setText(display[i])
                            amount3.setText(drinkOrder[i].toString() + " mL")
                            ingredient3.setVisibility(View.VISIBLE)
                            amount3.setVisibility(View.VISIBLE)
                        }
                        3 ->{
                            ingredient4.setText(display[i])
                            amount4.setText(drinkOrder[i].toString() + " mL")
                            ingredient4.setVisibility(View.VISIBLE)
                            amount4.setVisibility(View.VISIBLE)
                        }
                        4 ->{
                            ingredient5.setText(display[i])
                            amount5.setText(drinkOrder[i].toString() + " mL")
                            ingredient5.setVisibility(View.VISIBLE)
                            amount5.setVisibility(View.VISIBLE)
                        }
                        5 ->{
                            ingredient6.setText(display[i])
                            amount6.setText(drinkOrder[i].toString() + " mL")
                            ingredient6.setVisibility(View.VISIBLE)
                            amount6.setVisibility(View.VISIBLE)
                        }
                        6 ->{
                            ingredient7.setText(display[i])
                            amount7.setText(drinkOrder[i].toString() + " mL")
                            ingredient7.setVisibility(View.VISIBLE)
                            amount7.setVisibility(View.VISIBLE)
                        }
                        7 ->{
                            ingredient8.setText(display[i])
                            amount8.setText(drinkOrder[i].toString() + " mL")
                            ingredient8.setVisibility(View.VISIBLE)
                            amount8.setVisibility(View.VISIBLE)
                        }
                        8 ->{
                            ingredient9.setText(display[i])
                            amount9.setText(drinkOrder[i].toString() + " mL")
                            ingredient9.setVisibility(View.VISIBLE)
                            amount9.setVisibility(View.VISIBLE)
                        }
                        9 ->{
                            ingredient10.setText(display[i])
                            amount10.setText(drinkOrder[i].toString() + " mL")
                            ingredient10.setVisibility(View.VISIBLE)
                            amount10.setVisibility(View.VISIBLE)
                        }
                    }
                    j += 1
                }
            }
        }

        val nextButton = findViewById<Button>(R.id.confirmbutton)
        val backButton = findViewById<Button>(R.id.prevbutton)
        nextButton.setOnClickListener {
            val intent = Intent( this , Confirmed::class.java)
            intent.putExtra(key3, drinkOrder)
            intent.putExtra(key4, count)
            startActivity( intent )
        }
        backButton.setOnClickListener {
            val intent = Intent(this, Ingredients::class.java)
            intent.putExtra(key3, drinkOrder)
            intent.putExtra(key4, count)
            startActivity(intent)
        }
    }
}