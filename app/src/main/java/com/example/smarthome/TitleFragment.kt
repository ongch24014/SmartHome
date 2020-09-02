package com.example.smarthome


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.smarthome.databinding.FragmentTitleBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_title.*



/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title,container,false)

        binding.btnHomeLogin.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_loginFragment)
        }

        binding.btnDoor.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_doorFragment2)
        }

        binding.btnLight.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_lightFragment2)
        }

        binding.btnTemp.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_tempFragment2)
        }

        binding.btnAnalysis.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_analysisFragment3)
        }

        return binding.root
    }


}
