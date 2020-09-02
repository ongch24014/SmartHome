package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.core.view.marginRight
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.CommonResource
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentDoorBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_door.*
import kotlinx.android.synthetic.main.fragment_door.btnDoor
import kotlinx.android.synthetic.main.fragment_title.*
import java.util.*
import kotlin.concurrent.schedule

/**
 * A simple [Fragment] subclass.
 */
class DoorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDoorBinding>(inflater,
            R.layout.fragment_door,container,false)

    binding.btnDoor.setOnClickListener {
        if(txtDoorStatus.text.equals("DOOR UNLOCKED")){
            btnDoor.setBackgroundResource(R.drawable.round_button_red)
            btnDoor.setImageResource(R.drawable.door_locked)
            txtDoorStatus.text = "  DOOR LOCKED"
            txtDoorStatus.setTextColor("#9B1252".toColorInt()) //hey




            var database = FirebaseDatabase.getInstance().reference

            database.child("PI_01_CONTROL").addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val commonResource = dataSnapshot.getValue(CommonResource::class.java)
                    Log.d("Value",commonResource!!.lcdtext)
                    txtDoorStatus.text=commonResource!!.lcdtext
                }

            })
//
//            var getdata = object : ValueEventListener {
//                override fun onCancelled(dataSnapshot: DatabaseError) {
//
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//
//
//                    val gg = snapshot.child("PI_01_CONTROL").child("buzzer").value
//                    Log.d("Value",gg.toString())
//                    please = gg.toString()
//
//                }
//            }
//
//            txtDoorStatus.text = please
//            //database.addValueEventListener(getdata)
//            database.addListenerForSingleValueEvent(getdata)

        }

        else{

            btnDoor.setBackgroundResource(R.drawable.round_button_green)
            btnDoor.setImageResource(R.drawable.door_unlock)
            txtDoorStatus.text = "DOOR UNLOCKED"
            txtDoorStatus.setTextColor("#129B19".toColorInt())

            var database = FirebaseDatabase.getInstance().reference

            database.child("PI_01_CONTROL").child("led").setValue("0")


        }

    }

        binding.btnCapture.setOnClickListener{ view : View ->

            var database = FirebaseDatabase.getInstance().reference

            database.child("PI_01_CONTROL").child("camera").setValue("1")

            var year:Int = 0
            var month:Int = 0
            var day:Int = 0
            var hour:Int = 0
            var minute:Int = 0
            var second:Int = 0
            var full:String = ""

            year = Calendar.getInstance().get(Calendar.YEAR)
            month = Calendar.getInstance().get(Calendar.MONTH) + 1
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            minute = Calendar.getInstance().get(Calendar.MINUTE)
            second = (((Calendar.getInstance().get(Calendar.SECOND) / 10) + 1) * 10)

            Log.d("Value",Calendar.getInstance().get(Calendar.YEAR).toString())
            Log.d("Value",Calendar.getInstance().get(Calendar.MONTH).toString())
            Log.d("Value",Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString())
            Log.d("Value",Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toString())
            Log.d("Value",Calendar.getInstance().get(Calendar.MINUTE).toString())
            Log.d("Value",Calendar.getInstance().get(Calendar.SECOND).toString())

            if(second == 60){
                second = 0
                minute = minute + 1
            }

            //cam_20200902000710.jpg
            full = "cam_" + year.toString() + String.format("%02d",month) + String.format("%02d",day) + String.format("%02d",hour) + String.format("%02d",minute) + String.format("%02d",second) + ".jpg"

            val toast = Toast.makeText(context, "Capturing, Please wait...", Toast.LENGTH_LONG)
            toast.show()

            Timer().schedule(15000){
                Looper.prepare()
                Log.d("Value",full)
                database.child("PI_01_CONTROL").child("camera").setValue("0")

                view.findNavController().navigate(DoorFragmentDirections.actionDoorFragmentToCaptureFragment(full)
                    )

            }



        }

        return binding.root
    }

}
