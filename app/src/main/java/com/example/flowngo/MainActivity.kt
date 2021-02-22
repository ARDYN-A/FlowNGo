package com.example.flowngo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val key = "POSITION"

class MainActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener{

    private val drinkList: MutableList<Item> = mutableListOf<Item>()
    private val dAdapter = ItemsAdapter(drinkList, this)
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = dAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        generateDrinkList(3)
        dAdapter.notifyDataSetChanged()

        val nextButton = findViewById<Button>(R.id.nextbutton)

        nextButton.setOnClickListener {
            val intent = Intent( this , Ingredients::class.java)
            intent.putExtra(key, pos)
            startActivity( intent )
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = drinkList[position]
        pos = position
        if(drinkList[position].details == "Selected") {
            if(position == 0)
                drinkList[position].details = "A college classic."
            if(position == 1)
                drinkList[position].details = "Best served with tacos."
            if(position == 2)
                drinkList[position].details = "A classic drink for those who love quinine."
            if(position == 3)
                drinkList[position].details = "Don't ask me what it is man."
        } else {
            drinkList[position].details = "Selected"
        }
        dAdapter.notifyItemChanged(position)
    }

    private fun generateDrinkList(size: Int){

        drinkList.clear()
        for(i in 0 until size){
            val drawable = when (i){
                0       ->  R.drawable.ic_drink_screwdriver
                1       ->  R.drawable.ic_drink_margarita
                2       ->  R.drawable.ic_drink_gandt
                else    ->  R.drawable.ic_drink_mystery
            }
            val drinkName = when (i){
                0       ->  "Screwdriver"
                1       ->  "Margarita"
                2       ->  "Gin and Tonic"
                else    ->  "Mystery Juice"
            }
            val drinkDesc = when (i){
                0       ->  "A college classic."
                1       ->  "Best served with tacos."
                2       ->  "A classic drink for those who love quinine."
                else    ->  "Don't ask me what it is man."
            }
            val item = Item(drawable, drinkName, drinkDesc)
            drinkList.add(item)
        }
    }
}