package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.NullPointerException

private const val key1 = "DRINK_CHOICE"
private const val key2 = "SELECTED_INGREDIENT"
private const val key3 = "SERVINGS"

class Ingredients : AppCompatActivity(), ItemsAdapter.OnItemClickListener{

    private var drinkChoice = -1
    private var selectedIngredient = -1
    private var drinkOrder: DoubleArray = DoubleArray(10)
    private var ingredientList: MutableList<Item> = mutableListOf<Item>()
    private var iAdapter = ItemsAdapter(ingredientList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = iAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        drinkChoice = intent.getIntExtra(key1, -1)
        if(intent.getDoubleArrayExtra(key3) != null) {
            drinkOrder = intent.getDoubleArrayExtra(key3) as DoubleArray
        }
        generateIngredientList()

        iAdapter.notifyDataSetChanged()

        val nextButton = findViewById<Button>(R.id.nextbutton)
        val backButton = findViewById<Button>(R.id.backbutton)
        nextButton.setOnClickListener {
            val intent = Intent( this, Confirmed::class.java)
            intent.putExtra(key3, drinkOrder)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(key1, drinkChoice)
            startActivity(intent)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = ingredientList[position]
        selectedIngredient = position
        if(clickedItem.selected == View.INVISIBLE){
            val intent = Intent(this, Servings::class.java)
            intent.putExtra(key2, selectedIngredient)
            intent.putExtra(key3, drinkOrder)
            startActivity(intent)
        }
        else{
            clickedItem.selected = View.INVISIBLE
            drinkOrder[position] = 0.0
        }
        iAdapter.notifyDataSetChanged()
    }

    private fun generateIngredientList(){
        ingredientList.clear()
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Vodka", "Drunk for cheap.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tequila", "Your pants may fall off.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Gin", "Imagine making alcohol with a poisonous berry.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Whiskey", "WhiskAYYYYYY!", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Rum", "Sparrow's favorite.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Orange Juice", "Who doesn't love it?", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Triple Sec", "Alcoholic sugar water.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Lime Juice", "Boy is it sour.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tonic Water", "Mmm... quinine.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Coca Cola", "Polar bears say its great.", View.INVISIBLE))
        when (drinkChoice) {
            1 -> {
                drinkOrder[0] = 50.0
                drinkOrder[5] = 150.0
            }
            2 -> {
                drinkOrder[1] = 50.0
                drinkOrder[6] = 50.0
                drinkOrder[7] = 50.0
            }
            3 -> {
                drinkOrder[2] = 50.0
                drinkOrder[8] = 150.0
            }
            4 -> {
                drinkOrder[3] = 50.0
                drinkOrder[9] = 150.0
            }
            5 -> {
                drinkOrder[4] = 50.0
                drinkOrder[9] = 150.0
            }
        }
        for(i in 0 until 10){
            if(drinkOrder[i] != 0.0){
                ingredientList[i].selected = View.VISIBLE
            }
        }
    }
}