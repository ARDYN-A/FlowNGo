package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val key1 = "DRINK_CHOICE"
class MainActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener{

    private val drinkList: MutableList<Item> = mutableListOf<Item>()
    private val dAdapter = ItemsAdapter(drinkList, this)
    private var drinkChoice: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = dAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        drinkChoice = intent.getIntExtra(key1, -1)
        generateDrinkList(6)
        dAdapter.notifyDataSetChanged()

        val nextButton = findViewById<Button>(R.id.nextbutton)

        nextButton.setOnClickListener {
            val intent = Intent( this , Ingredients::class.java)
            intent.putExtra(key1, drinkChoice)
            startActivity( intent )
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = drinkList[position]
        for(item in drinkList){
            item.selected = View.INVISIBLE
        }
        drinkChoice = position
        clickedItem.selected = View.VISIBLE
        dAdapter.notifyDataSetChanged()
    }

    private fun generateDrinkList(size: Int){

        drinkList.clear()
        for(i in 0 until size){
            val drawable = when (i){
                0       ->  R.drawable.ic_drink_mystery
                1       ->  R.drawable.ic_drink_screwdriver
                2       ->  R.drawable.ic_drink_margarita
                3       ->  R.drawable.ic_drink_gandt
                4       ->  R.drawable.ic_ingredient_alcohol
                5       ->  R.drawable.ic_ingredient_alcohol
                else    ->  R.drawable.ic_drink_mystery
            }
            val drinkName = when (i){
                0       ->  "Custom Drink"
                1       ->  "Screwdriver"
                2       ->  "Margarita"
                3       ->  "Gin and Tonic"
                4       ->  "Whiskey and Coke"
                5       ->  "Rum and Coke"
                else    ->  "Mystery Juice"
            }
            val drinkDesc = when (i){
                0       ->  "Choose among the finest ingredients!"
                1       ->  "A college classic."
                2       ->  "Best served with tacos."
                3       ->  "A classic drink for those who love quinine."
                4       ->  "Read the drink name again."
                5       ->  "Cousin to the Whiskey and Coke."
                else    ->  "Don't ask me what it is man."
            }
            val item = Item(drawable, drinkName, drinkDesc, View.INVISIBLE)
            drinkList.add(item)
        }
    }
}