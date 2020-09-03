package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentDoorBinding
import com.example.smarthome.databinding.FragmentDoorScenarioBinding

/**
 * A simple [Fragment] subclass.
 */
class DoorScenarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDoorScenarioBinding>(inflater,
            R.layout.fragment_door_scenario,container,false)

        return binding.root
    }


}
