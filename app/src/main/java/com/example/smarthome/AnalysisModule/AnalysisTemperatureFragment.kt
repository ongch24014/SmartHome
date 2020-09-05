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

        val application = requireNotNull(this.activity).application
        val dataSource1 = SmartHomeDatabase.getInstance(application).temperatureDatabaseDao
        val dataSource2 = SmartHomeDatabase.getInstance(application).temperature2DatabaseDao


        val countT1:Int = dataSource1.getCount() //count stored inside countCap
        val avgT1:Double = dataSource1.getTempAvg()
        val countT2:Int = dataSource2.getCount() //count stored inside countCap
        val avgT2:Double = dataSource2.getTempAvg()

        Log.d("Value",countT1.toString())
        Log.d("Value",avgT1.toString())

        binding.txtTemp1.setText("Number of times temperature in living room has been checked: " + countT2 );
        binding.txtTemp2.setText("Average Temperature in living room:"+ avgT2 );
        binding.txtTemp3.setText("Number of times temperature in bedroom has been checked: " + countT1 );
        binding.txtTemp4.setText("Average Temperature in bedroom:"+ avgT1 );







        return binding.root
    }


}
