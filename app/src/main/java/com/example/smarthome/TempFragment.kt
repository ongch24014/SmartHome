package com.example.smarthome


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.databinding.FragmentLoginBinding
import com.example.smarthome.databinding.FragmentTempBinding

/**
 * A simple [Fragment] subclass.
 */
class TempFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTempBinding>(inflater,
            R.layout.fragment_temp,container,false)

        binding.LivingR.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_tempFragment_to_livingRoomTempFragment)
        }


        return binding.root
    }


}
