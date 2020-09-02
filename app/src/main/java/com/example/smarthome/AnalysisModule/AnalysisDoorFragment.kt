package com.example.smarthome.AnalysisModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentAnalysisDoorBinding
import com.example.smarthome.databinding.FragmentDoorBinding

/**
 * A simple [Fragment] subclass.
 */
class AnalysisDoorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAnalysisDoorBinding>(inflater,
            R.layout.fragment_analysis_door,container,false)

        //this is a test

        return binding.root
    }


}
