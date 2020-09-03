package com.example.smarthome.SmartDoorModule


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.potensituitionapp.database.Door
import com.example.smarthome.MainActivity
import com.example.smarthome.MainActivity.Companion.bellRing

import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
import com.example.smarthome.databinding.FragmentDoorBinding
import com.example.smarthome.databinding.FragmentDoorScenarioBinding
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.concurrent.schedule

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

        val handler = Handler()

        binding.btnRing.setOnClickListener{v: View? ->
            var database = FirebaseDatabase.getInstance().reference

            database.child("PI_01_CONTROL").child("camera").setValue("1")

            var year:Int
            var month:Int
            var day:Int
            var hour:Int
            var minute:Int
            var second:Int
            var full:String

            year = Calendar.getInstance().get(Calendar.YEAR)
            month = Calendar.getInstance().get(Calendar.MONTH) + 1
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            minute = Calendar.getInstance().get(Calendar.MINUTE)
            second = (((Calendar.getInstance().get(Calendar.SECOND) / 10) + 1) * 10)

            Log.d("Value", Calendar.getInstance().get(Calendar.YEAR).toString())
            Log.d("Value", Calendar.getInstance().get(Calendar.MONTH).toString())
            Log.d("Value", Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString())
            Log.d("Value", Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString())
            Log.d("Value", Calendar.getInstance().get(Calendar.MINUTE).toString())
            Log.d("Value", Calendar.getInstance().get(Calendar.SECOND).toString())

            if(second == 60){
                second = 0
                minute = minute + 1
            }

            //cam_20200902000710.jpg
            full = "cam_" + year.toString() + String.format("%02d",month) + String.format("%02d",day) + String.format("%02d",hour) + String.format("%02d",minute) + String.format("%02d",second) + ".jpg"

            val toast = Toast.makeText(context, "Ringing... Please wait around 10 secs...", Toast.LENGTH_LONG)
            toast.show()

            Timer().schedule(15000){

                bellRing = full

                Looper.prepare()
                Log.d("Value",full)
                database.child("PI_01_CONTROL").child("camera").setValue("0")

                handler.postDelayed(showSuccessToast,1500)

                val i = Intent(AlarmClock.ACTION_SET_TIMER)
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "BELL RUNG!")
                i.putExtra(AlarmClock.EXTRA_LENGTH, 2)
                i.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                startActivity(i)
            }

            val application = requireNotNull(this.activity).application
            val dataSource = SmartHomeDatabase.getInstance(application).doorDatabaseDao

            var door = Door()

            door.doorID = full
            door.year = year.toString()
            door.month = month.toString()
            door.day = day.toString()
            door.time = String.format("%02d",hour) + ":" + String.format("%02d",minute)

            dataSource.insert(door)

            var count:Int = dataSource.getCount()

            Log.d("Value",count.toString())
        }

        return binding.root
    }

    internal var showSuccessToast: Runnable = Runnable {
        val toast1 = Toast.makeText(context, "Success!", Toast.LENGTH_LONG)
        toast1.show()
    }


}
