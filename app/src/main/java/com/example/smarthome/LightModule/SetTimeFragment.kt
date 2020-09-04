package com.example.smarthome.LightModule

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Looper
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.example.smarthome.databinding.FragmentSetTimeBinding
import android.widget.Toast
import androidx.core.text.bold
import androidx.navigation.findNavController
import com.example.smarthome.CommonResource
import com.example.smarthome.SmartDoorModule.CaptureFragmentDirections
import com.example.smarthome.database.SmartHomeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_set_time.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.schedule


/**
 * A simple [Fragment] subclass.
 */
class SetTimeFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    //lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSetTimeBinding>(inflater,
            com.example.smarthome.R.layout.fragment_set_time,container,false)


        binding.timePicker1.setIs24HourView(false) // used to display AM/PM mode

        sharedPreferences = activity!!.getSharedPreferences("SelTime", Context.MODE_PRIVATE)

        // perform set on time changed listener event
        binding.timePicker1.setOnTimeChangedListener(TimePicker.OnTimeChangedListener { view, hourOfDay, minute ->

            var AM_PM = ""
            if (hourOfDay<12){
                AM_PM = "AM"
            } else
                AM_PM = "PM"

            val pattern = "HH:mm"
            val simpleDateFormat = SimpleDateFormat(pattern)

            val parser = SimpleDateFormat("H:m")
            val formatter = SimpleDateFormat("HH:mm")
            val formatter2 = SimpleDateFormat("mm")
            val selectedTime = formatter.format(parser.parse("$hourOfDay:$minute")!!)

           // val date = simpleDateFormat.format(selectedTime)
            binding.txtUserSelTime.text = selectedTime + " $AM_PM"

            //need format to HHmm
            val time = formatter2.format(parser.parse("$minute")!!)

            //save data
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putString("STRING_KEY", selectedTime)
                putString("Minute", time)
            }.apply()
            Toast.makeText(
                activity, sharedPreferences.getString("STRING_KEY", null), Toast.LENGTH_LONG
            ).show()
            Log.i("test",sharedPreferences.getString("STRING_KEY", null))
        })

        //if (binding.btnAutoOn.isClickable){
            binding.btnAutoOn.setOnClickListener {
                val selectedOption = SpannableStringBuilder().append("Turn ").bold {append("ON")}.append(" the light.").toString()
                binding.txtUserSelOption.text = selectedOption

                //save data
                val editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.apply{
                    putBoolean("Button_On", true)
                    putBoolean("Button_Off", false)
                }.apply()
//                Toast.makeText(
//                    activity, sharedPreferences.getBoolean("Button_On", false).toString(), Toast.LENGTH_LONG
//                ).show()
            }
        //}
        //if (binding.btnAutoOff.isClickable){
            binding.btnAutoOff.setOnClickListener {
                binding.txtUserSelOption.text = SpannableStringBuilder().append("Turn ").bold {append("OFF")}.append(" the light.")

                //save data
                val editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.apply{
                    putBoolean("Button_Off", true)
                    putBoolean("Button_On", false)
                }.apply()

            }
        //}




        binding.btnSet.setOnClickListener {
            CompareTime()
        }
        return binding.root
    }

    private fun CompareTime() {
        val currentTime = SimpleDateFormat("mm", Locale.getDefault()).format(Date())!!.toInt()
        val selTime = sharedPreferences.getString("Minute", null)!!.toInt()
        val optionOn:Boolean = sharedPreferences.getBoolean("Button_On", false)
        val optionOff:Boolean = sharedPreferences.getBoolean("Button_Off", false)

        val difTime = ((currentTime - selTime) * 60 * 1000).toString()

        var database = FirebaseDatabase.getInstance().reference

        Timer().schedule(difTime.toLong()){
            Looper.prepare()
            if(optionOn == true && optionOff == false){
                database.child("PI_01_CONTROL").child("led").setValue("1")
            }else {
                Log.i("testing", "off")
                database.child("PI_01_CONTROL").child("led").setValue("0")
            }

        }

//        Log.i("testing", sharedPreferences.getBoolean("Button_On", false).toString())
//        Log.i("testing", sharedPreferences.getBoolean("Button_Off", false).toString())
//
//        val savedString:String? = sharedPreferences.getString("STRING_KEY", null)
//        val optionOn:Boolean = sharedPreferences.getBoolean("Button_On", false)
//        val optionOff:Boolean = sharedPreferences.getBoolean("Button_Off", false)
//        var database = FirebaseDatabase.getInstance().reference
//
//        if (currentTime == savedString) {
//            if(optionOn == true && optionOff == false){
//                database.child("PI_01_CONTROL").child("led").setValue("1")
//            }else {
//                Log.i("testing", "off")
//                database.child("PI_01_CONTROL").child("led").setValue("0")
//
//            }
//        }
    }

    private fun loadData(){
        //loadData
        val savedString:String? = sharedPreferences.getString("STRING_KEY", null)
        //Log.i("testing", sharedPreferences.getString("STRING_KEY", null))

    }
}
