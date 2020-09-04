package com.example.smarthome.AnalysisModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
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

        val application = requireNotNull(this.activity).application
        val dataSource1 = SmartHomeDatabase.getInstance(application).lights1DatabaseDao
        val dataSource2 = SmartHomeDatabase.getInstance(application).lights2DatabaseDao
        val dataSource3 = SmartHomeDatabase.getInstance(application).lights3DatabaseDao

        var countL1:Int = dataSource1.getCount() //count stored inside countCap
        var countL2:Int = dataSource2.getCount() //count stored inside countLock
        var countL3:Int = dataSource3.getCount() //count stored inside countUnlock

        Log.d("Value",countL1.toString())
        Log.d("Value",countL2.toString())
        Log.d("Value",countL3.toString())

        binding.txtDevice1.setText("Number of times Light 1 has been turned on: " + countL1 );
        binding.txtDevice2.setText("Number of times Light 2 has been turned on: " + countL2 );
        binding.txtDevice3.setText("Number of times Light 3 has been turned on: " + countL3 );






        return binding.root
    }


}
