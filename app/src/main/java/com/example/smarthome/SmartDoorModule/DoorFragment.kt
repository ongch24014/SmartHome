package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.view.marginRight
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentDoorBinding
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
