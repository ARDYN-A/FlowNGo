package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        for(item in ingredientList){
            item.selected = View.INVISIBLE
        }
        pos = position
        clickedItem.selected = View.VISIBLE
        iAdapter.notifyDataSetChanged()
    }

    private fun generateIngredientList(){
        ingredientList.clear()
        if(pos == 0){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Vodka", "Drunk for cheap.", View.INVISIBLE))
            ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Orange Juice", "Who doesn't love it?", View.INVISIBLE))
        }
        else if(pos == 1){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tequila", "Your pants may fall off.", View.INVISIBLE))
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Triple Sec", "Alcoholic sugar water.", View.INVISIBLE))
            ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Lime Juice", "Boy is it sour.", View.INVISIBLE))
        }
        else if(pos == 2){
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Gin", "Imagine making alcohol with a poisonous berry.", View.INVISIBLE))
            ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Tonic Water", "Mmm... quinine.", View.INVISIBLE))
        }
        else{
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Mystery Ingredient", "Spooky", View.INVISIBLE))
        }

    }
}