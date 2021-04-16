package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val key1 = "DRINK_CHOICE"
private const val key2 = "SELECTED_INGREDIENT"
private const val key3 = "SERVINGS"
private const val key4 = "COUNT"

class Ingredients : AppCompatActivity(), ItemsAdapter.OnItemClickListener{

    private var drinkChoice = -1
    private var selectedIngredient = -1
    private var drinkOrder: DoubleArray = DoubleArray(10)
    private var ingredientList: MutableList<Item> = mutableListOf<Item>()
    private var iAdapter = ItemsAdapter(ingredientList, this)
    private val db = Firebase.database
    private lateinit var countListener: ValueEventListener
    private var count: Long = 0
    private fun listenForCountValueChanges(){
        countListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                count = dataSnapshot.value as Long
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Toast.makeText(applicationContext, "Firebase read cancelled.", Toast.LENGTH_SHORT).show()
            }
        }
        db.getReference("Count").addValueEventListener(countListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        listenForCountValueChanges()

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
        val backButton = findViewById<Button>(R.id.adminbutton)
        nextButton.setOnClickListener {
            val intent = Intent( this, dowhateveryouwant::class.java)
            intent.putExtra(key3, drinkOrder)
            intent.putExtra(key4, count)
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
        ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Add your own", "Add something else!", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Triple Sec", "Alcoholic sugar water.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Lime Juice", "Boy is it sour.", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_citrus, "Orange Juice", "Who doesn't love it?", View.INVISIBLE))
        ingredientList.add(Item(R.drawable.ic_ingredient_alcohol, "Cranberry Juice", "Dry out your throat.", View.INVISIBLE))
        when (drinkChoice) {
            1 -> {
                drinkOrder[0] = 50.0
                drinkOrder[8] = 150.0
            }
            2 -> {
                drinkOrder[1] = 60.0
                drinkOrder[6] = 50.0
                drinkOrder[7] = 40.0
            }
            3 -> {
                drinkOrder[0] = 50.0
                drinkOrder[9] = 150.0
            }
            4 -> {
                drinkOrder[4] = 50.0
                drinkOrder[8] = 150.0
            }
            5 -> {
                drinkOrder[4] = 50.0
                drinkOrder[5] = 150.0
            }
            6 -> {
                drinkOrder[3] = 100.0
            }
            7 -> {
                drinkOrder[0] = 50.0
                drinkOrder[6] = 30.0
                drinkOrder[7] = 10.0
                drinkOrder[9] = 10.0
            }
            8 -> {
                drinkOrder[0] = 30.0
                drinkOrder[1] = 30.0
                drinkOrder[2] = 30.0
                drinkOrder[4] = 30.0
                drinkOrder[5] = 100.0
                drinkOrder[6] = 20.0
            }
            9 -> {
                drinkOrder[2] = 75.0
                drinkOrder[7] = 30.0
            }
            10 -> {
                drinkOrder[2] = 50.0
                drinkOrder[5] = 100.0
                drinkOrder[7] = 10.0
            }
            11 -> {
                drinkOrder[3] = 50.0
                drinkOrder[5] = 150.0
            }
        }
        for(i in 0 until 10){
            if(drinkOrder[i] != 0.0){
                ingredientList[i].selected = View.VISIBLE
            }
        }
    }
}