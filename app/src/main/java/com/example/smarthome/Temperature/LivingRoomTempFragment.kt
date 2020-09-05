package com.example.smarthome.Temperature

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.potensituitionapp.database.Temperature
import com.example.potensituitionapp.database.Temperature2
import com.example.smarthome.CommonResourcesData
import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
import com.example.smarthome.databinding.FragmentLivingRoomTempBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LivingRoomTempFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LivingRoomTempFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLivingRoomTempBinding>(inflater,
            R.layout.fragment_living_room_temp,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource1 = SmartHomeDatabase.getInstance(application).temperature2DatabaseDao
        var temperature2 = Temperature2()

        var year:Int
        var month:Int
        var day:Int
        var hour:Int
        var minute:Int
        var second:Int
        var full:String
        second = (((Calendar.getInstance().get(Calendar.SECOND) / 10) + 1) * 10)
        second = second - 10

        year = Calendar.getInstance().get(Calendar.YEAR)
        month = Calendar.getInstance().get(Calendar.MONTH) + 1
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        minute = Calendar.getInstance().get(Calendar.MINUTE)

        if(second == 60){
            second = 0
            minute = minute + 1
        }

        var child1 = "PI_01_" + year + String.format("%02d",month) + String.format("%02d",day)
        var child2 = String.format("%02d",hour)
        var child3 = String.format("%02d",minute) + String.format("%02d",second)


        var database = FirebaseDatabase.getInstance().reference

        database.child(child1).child(child2).child(child3).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val commonResource = dataSnapshot.getValue(CommonResourcesData::class.java)
                Log.d("Value",commonResource!!.tempe)
                val tempe:String = commonResource!!.tempe
                binding.txtTemp.text = tempe
                temperature2.temperatureReading = tempe
                dataSource1.insert(temperature2)
            }
        })

        return binding.root

    }
}