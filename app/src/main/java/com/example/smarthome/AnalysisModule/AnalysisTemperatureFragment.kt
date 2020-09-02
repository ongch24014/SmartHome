package com.example.smarthome.AnalysisModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentAnalysisTemperatureBinding

/**
 * A simple [Fragment] subclass.
 */
class AnalysisTemperatureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAnalysisTemperatureBinding>(inflater,
            R.layout.fragment_analysis_temperature,container,false)

        return binding.root
    }


}
