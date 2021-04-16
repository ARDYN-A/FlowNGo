package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = dAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        drinkChoice = intent.getIntExtra(key1, -1)
        generateDrinkList(12)
        dAdapter.notifyDataSetChanged()

        val nextButton = findViewById<Button>(R.id.nextbutton)
        val adminButton = findViewById<Button>(R.id.adminbutton)
        nextButton.setOnClickListener {
            val intent = Intent( this , Ingredients::class.java)
            intent.putExtra(key1, drinkChoice)
            startActivity( intent )
        }
        adminButton.setOnClickListener {
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
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
                6       ->  R.drawable.ic_ingredient_alcohol
                7       ->  R.drawable.ic_ingredient_alcohol
                8       ->  R.drawable.ic_ingredient_alcohol
                9       ->  R.drawable.ic_ingredient_alcohol
                10      ->  R.drawable.ic_ingredient_alcohol
                11      ->  R.drawable.ic_ingredient_alcohol
                else    ->  R.drawable.ic_drink_mystery
            }
            val drinkName = when (i){
                0       ->  "Custom Drink"
                1       ->  "Screwdriver"
                2       ->  "Margarita"
                3       ->  "Vodka Cran"
                4       ->  "Rum Sunset"
                5       ->  "Malibu Cola"
                6       ->  "Whiskey"
                7       ->  "Cosmopolitan"
                8       ->  "Long Island Iced Tea"
                9       ->  "Gimlet"
                10      ->  "Gin and Tonic"
                11      ->  "Whiskey Coke"
                else    ->  "Mystery Juice"
            }
            val drinkDesc = when (i){
                0       ->  "Choose among the finest ingredients!"
                1       ->  "A college classic."
                2       ->  "Best served with tacos."
                3       ->  "A classic drink."
                4       ->  "Rum and orange juice!"
                5       ->  "Rum and coke. Just add coke!"
                6       ->  "Plain. How it should be."
                7       ->  "For the ladies"
                8       ->  "Splash of everything. Just add coke!"
                9       ->  "Gin and lime"
                10      ->  "Just add tonic!"
                11      ->  "Doubt anyone orders this. Just add coke!"
                else    ->  "Don't ask me what it is man."
            }
            val item = Item(drawable, drinkName, drinkDesc, View.INVISIBLE)
            drinkList.add(item)
        }
    }
}