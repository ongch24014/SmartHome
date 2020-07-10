package com.example.smarthome.Login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentLightBinding
import com.example.smarthome.databinding.FragmentLoginBinding
import com.example.smarthome.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,
            R.layout.fragment_login,container,false)

        binding.btnLights.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_lightFragment)
        }
        return binding.root
    }


}
