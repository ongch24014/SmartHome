package com.example.smarthome.AnalysisModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentAnalysisBinding

/**
 * A simple [Fragment] subclass.
 */
class AnalysisFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAnalysisBinding>(inflater,
            R.layout.fragment_analysis,container,false)

        binding.btnDoor.setOnClickListener { v: View? ->
            view!!.findNavController().navigate(R.id.action_analysisFragment_to_analysisDoorFragment2)
        }

        binding.btnLight.setOnClickListener { v: View? ->
            view!!.findNavController().navigate(R.id.action_analysisFragment_to_analysisLightFragment)
        }

        binding.btnTemp.setOnClickListener { v: View? ->
            view!!.findNavController().navigate(R.id.action_analysisFragment_to_analysisTemperatureFragment)
        }


        return binding.root
    }


}
