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
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentOpenCloseLightBindingImpl
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
            Log.i("test","123")
            view.findNavController().navigate(R.id.action_openCloseLightFragment_to_lightSettingFragment)
        }

        binding.switch1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch1, isChecked ->
            if (isChecked){
                binding.imgLight.setImageResource(R.drawable.ic_active_light)
            } else {
                binding.imgLight.setImageResource(R.drawable.ic_inactive_light)
            }
        })

        binding.switch2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { switch2, isChecked ->
            if (isChecked){
                binding.imgLight2.setImageResource(R.drawable.ic_active_light)
            } else {
                binding.imgLight2.setImageResource(R.drawable.ic_inactive_light)
            }
        })

        binding.btnCloseAll.setOnClickListener{
            binding.imgLight.setImageResource(R.drawable.ic_inactive_light)
            binding.imgLight2.setImageResource(R.drawable.ic_inactive_light)
            binding.switch1.isChecked = if(binding.switch1.isChecked)false else true
            binding.switch2.isChecked = if(binding.switch2.isChecked)false else true
        }

        return binding.root
    }


}
