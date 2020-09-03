package com.example.smarthome.AnalysisModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentAnalysisLightBinding

/**
 * A simple [Fragment] subclass.
 */
class AnalysisLightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAnalysisLightBinding>(inflater,
            R.layout.fragment_analysis_light,container,false)

        return binding.root
    }


}
