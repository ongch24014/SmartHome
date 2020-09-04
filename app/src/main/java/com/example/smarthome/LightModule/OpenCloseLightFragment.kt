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
import com.example.smarthome.R
import com.example.smarthome.database.SmartHomeDatabase
import com.example.smarthome.databinding.FragmentOpenCloseLightBindingImpl
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_open_close_light.view.*


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
        val dataSource = SmartHomeDatabase.getInstance(application).lightsDatabaseDao
        var count = 0

        binding.switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch1, isChecked ->
            if (isChecked){
                binding.imgLight.setImageResource(R.drawable.ic_active_light)
                database.child("PI_01_CONTROL").child("led").setValue("1")

                count ++
                Log.i("count1", count.toString())

//                var light = Lights()
//                light.count = count
//                dataSource.insert(light)
//
//                var count1:Int = dataSource.getCount() //count stored inside count1
//                Log.d("Value",count1.toString())

            } else {
                binding.imgLight.setImageResource(R.drawable.ic_inactive_light)
                database.child("PI_01_CONTROL").child("led").setValue("0")
            }
        })

        binding.switch2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch2, isChecked ->
            if (isChecked){
                binding.imgLight2.setImageResource(R.drawable.ic_active_light)
                count ++
                Log.i("count2", count.toString())
            } else {
                binding.imgLight2.setImageResource(R.drawable.ic_inactive_light)
            }
        })

        binding.switch3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch3, isChecked ->
            if (isChecked){
                binding.imgLight3.setImageResource(R.drawable.ic_active_light)
                count ++
                Log.i("count3", count.toString())
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
