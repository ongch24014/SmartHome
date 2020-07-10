package com.example.smarthome.LightModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentLightBinding
import com.example.smarthome.databinding.FragmentLoginBinding

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

        return binding.root
    }


}
