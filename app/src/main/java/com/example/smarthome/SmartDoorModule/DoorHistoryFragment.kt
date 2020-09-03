package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potensituitionapp.database.Door

import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
import com.example.smarthome.databinding.FragmentDoorHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class DoorHistoryFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDoorHistoryBinding>(inflater,
            R.layout.fragment_door_history,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = SmartHomeDatabase.getInstance(application).doorDatabaseDao

        val abc = dataSource.getDoors()

        binding.doorList.layoutManager = LinearLayoutManager(this.context)
        binding.doorList.adapter = DoorHistoryAdapter(dataSource.getDoors(),this.activity)

        return binding.root
    }

}

