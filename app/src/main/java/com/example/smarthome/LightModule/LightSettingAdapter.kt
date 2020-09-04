package com.example.smarthome.LightModule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.potensituitionapp.database.Lights
import com.example.smarthome.R

class LightSettingAdapter (private var lights:List<Lights>, var view: FragmentActivity?) :
    RecyclerView.Adapter<LightSettingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtDateTime: TextView = itemView.findViewById(R.id.txtDateTime)
        val txtTimeSet: TextView = itemView.findViewById(R.id.txtTimeSet)
        val txtOption: TextView = itemView.findViewById(R.id.txtOption)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_set_time, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lights.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtDateTime.text = lights[position].day + "/" + lights[position].month + "/" + lights[position].year + " " + lights[position].time
        holder.txtTimeSet.text = "Selected Time: " + lights[position].selectedTime
        holder.txtOption.text = lights[position].option

    }

}
