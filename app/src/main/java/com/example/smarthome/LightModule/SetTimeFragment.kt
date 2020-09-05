package com.example.smarthome.LightModule

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Looper
import android.preference.PreferenceManager
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
import com.example.potensituitionapp.database.Lights
import com.example.smarthome.CommonResource
import com.example.smarthome.R
import com.example.smarthome.SmartDoorModule.CaptureFragmentDirections
import com.example.smarthome.database.SmartHomeDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_set_time.*
import java.text.SimpleDateFormat
import java.time.LocalDate
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

            val parser = SimpleDateFormat("H:m")
            val formatter = SimpleDateFormat("HH:mm")
            val selectedTime = formatter.format(parser.parse("$hourOfDay:$minute")!!)
            binding.txtUserSelTime.text = selectedTime + " $AM_PM"


            //save data
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putString("STRING_KEY", selectedTime)
            }.apply()
            Toast.makeText(
                activity, sharedPreferences.getString("STRING_KEY", null), Toast.LENGTH_LONG
            ).show()
        })

        binding.btnAutoOn.setOnClickListener {
            val selectedOption = SpannableStringBuilder().append("Turn ").bold {append("ON")}.append(" the light.").toString()
            binding.txtUserSelOption.text = selectedOption

            //save data
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("Button_On", true)
                putBoolean("Button_Off", false)
            }.apply()
        }

        binding.btnAutoOff.setOnClickListener {
            binding.txtUserSelOption.text = SpannableStringBuilder().append("Turn ").bold {append("OFF")}.append(" the light.")

            //save data
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("Button_Off", true)
                putBoolean("Button_On", false)
            }.apply()

        }

        binding.btnSet.setOnClickListener {
            compareTime()
            // updateDBCurrentDate()
            Toast.makeText(activity?.getApplicationContext(),"Add successful",Toast.LENGTH_SHORT).show();
            view!!.findNavController().navigate(R.id.action_setTimeFragment_to_lightSettingFragment)
        }

        return binding.root
    }

    private fun validateTime():Boolean{
        var time:String? = sharedPreferences.getString("STRING_KEY", null)

        var selecthour = time!!.take(2)
        var selmin = time!!.takeLast(2)
        var hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        var minute = Calendar.getInstance().get(Calendar.MINUTE)

        var hournow = String.format("%02d",hour)
        var minnow = String.format("%02d",minute)

        if (txtUserSelTime.text == "selected time") {
            txtError1.visibility = View.VISIBLE
            return false
        } else if (txtUserSelTime.text != "selected time"){
            if (selecthour < hournow){
                txtError1.visibility = View.VISIBLE
                txtError1.setText("Time before current time is not allowed.")
                return false
            }
            else if (selecthour == hournow){
                if (selmin < minnow){
                    txtError1.visibility = View.VISIBLE
                    txtError1.setText("Time before current time is not allowed.")
                    return false
                } else {
                    txtError1.visibility = View.GONE
                    return true
                }
            }
            else{
                txtError1.visibility = View.GONE
                return true
            }
        }
        else {
            txtError1.visibility = View.GONE
            return true
        }
    }

    private fun validateOption():Boolean {
         if (txtUserSelOption.text == "selected option"){
            txtError2.visibility = View.VISIBLE
             return false
         }
         else {
             txtError2.visibility = View.GONE
             return true
         }

    }

    private fun compareTime() {
       // Log.i("test",validateTime().toString()+ "123")
        //Log.i("test",validateOption().toString())
//
      //  if (!validateTime() || !validateOption()){
         //  return
      // }
        //else
        //    Log.i("test", validateOption().toString())

            updateDBCurrentDate()

            var getmin:String? = sharedPreferences.getString("STRING_KEY", null)
            getmin = getmin?.takeLast(2)
            var minnow = Calendar.getInstance().get(Calendar.MINUTE)
            val optionOn:Boolean = sharedPreferences.getBoolean("Button_On", false)
            val optionOff:Boolean = sharedPreferences.getBoolean("Button_Off", false)
            var database = FirebaseDatabase.getInstance().reference

            var getsec:Int? = (getmin!!.toInt() - minnow) * 60000


            Timer().schedule(getsec!!.toLong()){
                Looper.prepare()
                if(optionOn == true && optionOff == false){
                    database.child("PI_01_CONTROL").child("led").setValue("1")

                }else {
                    database.child("PI_01_CONTROL").child("led").setValue("0")

                }

            }

    }

    private fun updateDBCurrentDate() {

        var year:Int
        var month:Int
        var day:Int
        var hour:Int
        var minute:Int
        var AM_PM = ""

        val optionOn:Boolean = sharedPreferences.getBoolean("Button_On", false)
        val optionOff:Boolean = sharedPreferences.getBoolean("Button_Off", false)

        year = Calendar.getInstance().get(Calendar.YEAR)
        month = Calendar.getInstance().get(Calendar.MONTH) + 1
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        minute = Calendar.getInstance().get(Calendar.MINUTE)

        if (hour<12){
            AM_PM = "AM"
        } else
            AM_PM = "PM"

        val application = requireNotNull(this.activity).application
        val dataSource = SmartHomeDatabase.getInstance(application).lightsDatabaseDao
        var light = Lights()

        light.day = day.toString()
        light.month = month.toString()
        light.year = year.toString()
        light.time = String.format("%02d",hour) + ":" + String.format("%02d",minute) + " " + AM_PM
        if(optionOn == true && optionOff == false){
            light.option = "Auto On"
        } else {
            light.option = "Auto Off"
        }
        Log.i("test", txtUserSelOption.text.toString())
        light.selectedTime = sharedPreferences.getString("STRING_KEY", null).toString()

        dataSource.insert(light)

    }

}
