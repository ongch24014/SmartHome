package com.example.smarthome.LightModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.smarthome.databinding.FragmentOpenCloseLightBinding
import android.widget.CompoundButton
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.potensituitionapp.database.Lights
import com.example.potensituitionapp.database.Lights1
import com.example.potensituitionapp.database.Lights2
import com.example.potensituitionapp.database.Lights3
import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
import com.example.smarthome.databinding.FragmentOpenCloseLightBindingImpl
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_open_close_light.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class OpenCloseLightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentOpenCloseLightBinding>(inflater,
            R.layout.fragment_open_close_light,container,false)

        binding.btnSetting.setOnClickListener{view : View ->
            Log.i("testing","123")
            view.findNavController().navigate(R.id.action_openCloseLightFragment_to_lightSettingFragment)
        }

        var database = FirebaseDatabase.getInstance().reference
        val application = requireNotNull(this.activity).application
        val dataSource1 = SmartHomeDatabase.getInstance(application).lights1DatabaseDao
        val dataSource2 = SmartHomeDatabase.getInstance(application).lights2DatabaseDao
        val dataSource3 = SmartHomeDatabase.getInstance(application).lights3DatabaseDao
        var count = 0

        binding.switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch1, isChecked ->
            if (isChecked){
                binding.imgLight.setImageResource(R.drawable.ic_active_light)
                database.child("PI_01_CONTROL").child("led").setValue("1")

//                count ++
//                Log.i("count1", count.toString())

                // add data into light1
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                var light1 = Lights1()
                light1.fulldate = currentDate.toString()
                dataSource1.insert(light1)

            } else {
                binding.imgLight.setImageResource(R.drawable.ic_inactive_light)
                database.child("PI_01_CONTROL").child("led").setValue("0")
            }
        })

        binding.switch2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch2, isChecked ->
            if (isChecked){
                binding.imgLight2.setImageResource(R.drawable.ic_active_light)

                // add data into light2
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                var light2 = Lights2()
                light2.fulldate = currentDate.toString()
                dataSource2.insert(light2)
            } else {
                binding.imgLight2.setImageResource(R.drawable.ic_inactive_light)
            }
        })

        binding.switch3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch3, isChecked ->
            if (isChecked){
                binding.imgLight3.setImageResource(R.drawable.ic_active_light)

                // add data into light3
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                var light3 = Lights3()
                light3.fulldate = currentDate.toString()
                dataSource3.insert(light3)
            } else {
                binding.imgLight3.setImageResource(R.drawable.ic_inactive_light)
            }
        })

        binding.btnCloseAll.setOnClickListener{
            binding.imgLight.setImageResource(R.drawable.ic_inactive_light)
            binding.imgLight2.setImageResource(R.drawable.ic_inactive_light)
            binding.switch1.setChecked(false)
            binding.switch2.setChecked(false)

        }

        return binding.root
    }


}
