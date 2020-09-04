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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smarthome.database.SmartHomeDatabase
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
            com.example.smarthome.R.layout.fragment_light_setting,container,false)


        binding.fabAdd.setOnClickListener { view ->
            view.findNavController().navigate(com.example.smarthome.R.id.action_lightSettingFragment_to_setTimeFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = SmartHomeDatabase.getInstance(application).lightsDatabaseDao

        binding.rvLightSetting.layoutManager = LinearLayoutManager(this.context)
        binding.rvLightSetting.adapter = LightSettingAdapter(dataSource.getLights(),this.activity)

//        val linearLayoutManager = LinearLayoutManager(activity)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        binding.rvLightSetting.setLayoutManager(linearLayoutManager)
//        binding.rvLightSetting.setNestedScrollingEnabled(false)
//        binding.rvLightSetting.setHasFixedSize(false)

        binding.rvLightSetting.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        return binding.root
    }


}
