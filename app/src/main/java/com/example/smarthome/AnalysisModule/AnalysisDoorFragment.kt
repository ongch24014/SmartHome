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
import com.example.smarthome.databinding.FragmentAnalysisDoorBinding


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

        val application = requireNotNull(this.activity).application
        val dataSource1 = SmartHomeDatabase.getInstance(application).doorDatabaseDao
        val dataSource2 = SmartHomeDatabase.getInstance(application).doorLockDatabaseDao
        val dataSource3 = SmartHomeDatabase.getInstance(application).doorUnlockDatabaseDao

        var countCap:Int = dataSource1.getCount() //count stored inside countCap
        var countLock:Int = dataSource2.getCount() //count stored inside countLock
        var countUnlock:Int = dataSource3.getCount() //count stored inside countUnlock

        Log.d("Value",countCap.toString())
        Log.d("Value",countLock.toString())
        Log.d("Value",countUnlock.toString())

        binding.txtUnlock.setText("Number of times the door has been unlocked: " + countUnlock.toString() );
        binding.txtLock.setText("Number of times the door has been locked: " + countLock.toString() );
        binding.txtCap.setText("Number of times an image has been captured: " + countCap.toString() );










        return binding.root
    }


}
