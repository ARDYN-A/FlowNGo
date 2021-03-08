package com.example.flowngo

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ItemsAdapter(
    private val values: List<Item>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.imageView.setImageResource(item.imageResource)
        holder.nameView.text = item.name
        holder.descView.text = item.details
        holder.checkView.visibility =  item.selected
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnClickListener{
        val imageView: ImageView = view.findViewById(R.id.image_view)
        val nameView: TextView = view.findViewById(R.id.name)
        val descView: TextView = view.findViewById(R.id.desc)
        val checkView: ImageView = view.findViewById(R.id.checkbox)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View){
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}