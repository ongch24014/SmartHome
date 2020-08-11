package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentDoorBinding

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

        return binding.root
    }


}
