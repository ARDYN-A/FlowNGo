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

        nextButton.setOnClickListener {
            val intent = Intent( this , ListItem::class.java)
            startActivity( intent )
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = ingredientList[position]
        iAdapter.notifyItemChanged(position)
    }

    private fun generateIngredientList(){
        ingredientList.clear()
        if(pos == 0){
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Vodka", "Drunk for cheap."))
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Orange Juice", "Who doesn't love it?"))
        }
        else if(pos == 1){
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Tequila", "Your pants may fall off."))
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Triple Sec", "Alcoholic sugar water."))
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Lime Juice", "Boy is it sour."))
        }
        else if(pos == 2){
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Gin", "Imagine making alcohol with a poisonous berry."))
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Tonic Water", "Mmm... quinine."))
        }
        else{
            ingredientList.add(Item(R.drawable.ic_drink_mystery, "Mystery Ingredient", "Spooky"))
        }

    }
}