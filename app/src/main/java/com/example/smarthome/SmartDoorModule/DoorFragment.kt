package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.view.marginRight
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
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
            txtDoorStatus.setTextColor("#9B1252".toColorInt())




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

        }

    }

        return binding.root
    }


}
