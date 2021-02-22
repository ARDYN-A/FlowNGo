package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val key = "POSITION"

class Ingredients : AppCompatActivity(), ItemsAdapter.OnItemClickListener{

    private var pos = -1
    private var ingredientList: MutableList<Item> = mutableListOf<Item>()
    private var iAdapter = ItemsAdapter(ingredientList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = iAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        pos = intent.getIntExtra(key, -1)
        generateIngredientList()

        iAdapter.notifyDataSetChanged()

        val nextButton = findViewById<Button>(R.id.nextbutton)
        val backButton = findViewById<Button>(R.id.backbutton)
        nextButton.setOnClickListener {
            val intent = Intent( this, Servings::class.java)
            intent.putExtra(key, pos)
            startActivity( intent )
        }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(key, pos)
            startActivity(intent)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = ingredientList[position]
        //pos = position
        if(ingredientList[position].details == "Unselected") {
            if(position == 0) {
                ingredientList[position].details = "Drunk for cheap."
                ingredientList[position].details = "Who doesn't love it?"
            }
            if(position == 1) {
                ingredientList[position].details = "Your pants may fall off."
                ingredientList[position].details = "Alcoholic sugar water."
                ingredientList[position].details = "Boy is it sour."
            }
            if(position == 2) {
                ingredientList[position].details = "Imagine making alcohol with a poisonous berry."
                ingredientList[position].details = "Mmm... quinine."
            }
            if(position == 3)
                ingredientList[position].details = "Don't ask me what it is man."
        } else {
            ingredientList[position].details = "Unselected"
        }
        iAdapter.notifyItemChanged(position)
    }

    private fun generateIngredientList(){
        ingredientList.clear()
        if(pos == 0){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Vodka", "Drunk for cheap."))
            ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Orange Juice", "Who doesn't love it?"))
        }
        else if(pos == 1){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tequila", "Your pants may fall off."))
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Triple Sec", "Alcoholic sugar water."))
            ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Lime Juice", "Boy is it sour."))
        }
        else if(pos == 2){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Gin", "Imagine making alcohol with a poisonous berry."))
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tonic Water", "Mmm... quinine."))
        }
        else{
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Mystery Ingredient", "Spooky"))
        }

    }
}