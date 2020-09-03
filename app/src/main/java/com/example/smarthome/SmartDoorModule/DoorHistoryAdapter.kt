package com.example.smarthome.SmartDoorModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.potensituitionapp.database.Door
import com.example.smarthome.R
import com.example.smarthome.TextItemViewHolder

class DoorHistoryAdapter (private var doorid: List<Door>):
    RecyclerView.Adapter<DoorHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.txtDoorId)

        init {
            itemView.setOnClickListener{ v: View? ->  
                val position: Int = adapterPosition
                Toast.makeText(itemView.context,"you click on # ${position + 1}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return doorid.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = doorid[position].doorID
    }




}