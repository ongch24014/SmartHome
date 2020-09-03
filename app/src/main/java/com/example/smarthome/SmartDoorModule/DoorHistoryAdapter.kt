package com.example.smarthome.SmartDoorModule

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.potensituitionapp.database.Door
import com.example.smarthome.MainActivity
import com.example.smarthome.R
import java.security.AccessController.getContext


class DoorHistoryAdapter (private var doorid: List<Door>, var view: FragmentActivity?):
    RecyclerView.Adapter<DoorHistoryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.txtDoorId)
        val itemTitle1: TextView = itemView.findViewById(R.id.txtDay)

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
        holder.itemTitle1.text = doorid[position].day + "/" + doorid[position].month + "/" + doorid[position].year

        holder.itemView.setOnClickListener { v: View? ->
            Log.i("test", doorid[position].doorID)

            val activity = (view as AppCompatActivity)
            val navController = Navigation.findNavController(activity, R.id.myNavHostFragment)
            navController.navigate(DoorHistoryFragmentDirections.actionDoorHistoryFragmentToCaptureFragment(doorid[position].doorID))
        }

    }

}
