package com.example.smarthome.SmartDoorModule

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.potensituitionapp.database.Door
import com.example.smarthome.MainActivity
import com.example.smarthome.R



class DoorHistoryAdapter (private var doorid: List<Door>, var view: View?):
    RecyclerView.Adapter<DoorHistoryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.txtDoorId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return doorid.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = doorid[position].doorID
        Log.i("test", "rightere")


        holder.itemView.setOnClickListener { v: View? ->
            Log.i("test", "clciked")

            //findNavController().navigate(R.id.action_doorHistoryFragment_to_captureFragment)

        }

    }


}
