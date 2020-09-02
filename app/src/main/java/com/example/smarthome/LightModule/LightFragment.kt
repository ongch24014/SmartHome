package com.example.smarthome.LightModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentLightBinding


/**
 * A simple [Fragment] subclass.
 */
class LightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentLightBinding>(inflater,
            R.layout.fragment_light,container,false)

        binding.cvLivingRoom.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_lightFragment_to_openCloseLightFragment)
        }

        return binding.root
    }


}
