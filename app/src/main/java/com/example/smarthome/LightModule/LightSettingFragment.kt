package com.example.smarthome.LightModule


import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentLightSettingBinding
import com.google.android.material.snackbar.Snackbar
import com.michaldrabik.classicmaterialtimepicker.CmtpDialogFragment
import com.michaldrabik.classicmaterialtimepicker.model.CmtpTime
import com.michaldrabik.classicmaterialtimepicker.model.CmtpTime12
import com.michaldrabik.classicmaterialtimepicker.utilities.setOnTime12PickedListener
import java.util.*



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
            com.example.smarthome.R.layout.fragment_light_setting,container,false)

//        val items = listOf("1", "2", "3", "4")
//        val adapter = ArrayAdapter(requireContext(), R.layout.fragment_light_setting, items)
//        (binding.txtHoursfield.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.fabAdd.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_lightSettingFragment_to_setTimeFragment)
        }

        return binding.root
    }


}
