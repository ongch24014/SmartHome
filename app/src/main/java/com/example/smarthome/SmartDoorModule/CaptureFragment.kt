package com.example.smarthome.SmartDoorModule


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide

import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentCaptureBinding
import com.example.smarthome.databinding.FragmentDoorBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * A simple [Fragment] subclass.
 */
class CaptureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCaptureBinding>(inflater,
            R.layout.fragment_capture,container,false)

        val storage = Firebase.storage("gs://bait2123-202006-01.appspot.com")
        var storageRef = storage.reference

        var spaceRef = storageRef.child("PI_01_CONTROL").child("cam_20200814155920.jpg")

        spaceRef.downloadUrl.addOnSuccessListener { it ->

            Log.d("Pic1",it.toString())
             var pic = it.toString()

            Glide.with(this.context /* context */)
                .load(pic)
                .into(binding.imageView)

        }




        return binding.root
    }


}
