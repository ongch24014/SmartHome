package com.example.smarthome.LightModule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.potensituitionapp.database.Lights
import com.example.smarthome.R
import com.example.smarthome.SmartDoorModule.DoorHistoryAdapter
import com.example.smarthome.SmartDoorModule.DoorHistoryFragmentDirections

class LightAdapter (private var lights:List<Lights>, var view: FragmentActivity?):
    RecyclerView.Adapter<LightAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtDateTime: TextView = itemView.findViewById(R.id.txtDateTime)
        val txtTimeSet: TextView = itemView.findViewById(R.id.txtTimeSet)
        val txtOption: TextView = itemView.findViewById(R.id.txtOption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_set_time, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lights.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDateTime.text = lights[position].day + "/" + lights[position].month + "/" + lights[position].year + "/" + lights[position].time
        holder.txtTimeSet.text = lights[position].selectedTime
        holder.txtOption.text = lights[position].option

    }

}