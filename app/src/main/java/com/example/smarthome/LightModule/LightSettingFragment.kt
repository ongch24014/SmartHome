package com.example.smarthome.LightModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentLightSettingBinding

/**
 * A simple [Fragment] subclass.
 */
class LightSettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentLightSettingBinding>(inflater,
            R.layout.fragment_light_setting,container,false)

        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), R.layout.fragment_light_setting, items)
        //(binding.txtHoursfield.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        return binding.root
    }


}
