package com.example.smarthome.Temperature


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.databinding.FragmentTempBinding
import com.example.smarthome.R
import kotlinx.android.synthetic.main.fragment_temp.view.*
import java.util.*


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

        binding.LivingR.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_tempFragment_to_livingRoomTempFragment)
        }
            binding.BedR.setOnClickListener {view : View ->
                view.findNavController().navigate(R.id.action_tempFragment_to_bedRoomTempFragment)
        }

        binding.DateTemperature.text = Calendar.getInstance().get(Calendar.DATE).toString() + "/" + Calendar.getInstance().get(Calendar.MONTH).toString() + "/" + Calendar.getInstance().get(Calendar.YEAR).toString() + "  " + Calendar.getInstance().get(Calendar.HOUR).toString() +":"+ Calendar.getInstance().get(Calendar.MINUTE).toString()





        return binding.root


    }


}


