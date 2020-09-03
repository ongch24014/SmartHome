package com.example.smarthome.SmartDoorModule


import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
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
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.concurrent.schedule
import android.graphics.Bitmap
import androidx.navigation.findNavController
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.potensituitionapp.database.Door
import com.example.smarthome.database.SmartHomeDatabase


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

        val handler = Handler()

        val args = CaptureFragmentArgs.fromBundle(arguments!!)

        val storage = Firebase.storage("gs://bait2123-202006-01.appspot.com")
        var storageRef = storage.reference

        var spaceRef = storageRef.child("PI_01_CONTROL").child(args.imageId)
        //var spaceRef = storageRef.child("PI_01_CONTROL").child("cam_20200903162420.jpg")
        Log.i("test","capture" + args.imageId)
        //var spaceRef = storageRef.child("PI_01_CONTROL").child("cam_20200903101200.jpg")

        spaceRef.downloadUrl.addOnSuccessListener { it ->

            Log.d("Pic1",it.toString())
             var pic = it.toString()

            Glide.with(this.context /* context */)
                .load(pic)
                .into(binding.imageView)

        }

        binding.btnCapture.setOnClickListener { view: View? ->
            val toast = Toast.makeText(context, "Re-capturing, Please wait...", Toast.LENGTH_LONG)
            toast.show()

            var database = FirebaseDatabase.getInstance().reference

            database.child("PI_01_CONTROL").child("camera").setValue("1")

            var year:Int = 0
            var month:Int = 0
            var day:Int = 0
            var hour:Int = 0
            var minute:Int = 0
            var second:Int = 0
            var full:String = ""

            year = Calendar.getInstance().get(Calendar.YEAR)
            month = Calendar.getInstance().get(Calendar.MONTH) + 1
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            minute = Calendar.getInstance().get(Calendar.MINUTE)
            second = (((Calendar.getInstance().get(Calendar.SECOND) / 10) + 1) * 10)

            if(second == 60){
                second = 0
                minute = minute + 1
            }

            //cam_20200902000710.jpg
            full = "cam_" + year.toString() + String.format("%02d",month) + String.format("%02d",day) + String.format("%02d",hour) + String.format("%02d",minute) + String.format("%02d",second) + ".jpg"


            Timer().schedule(15000){
                Looper.prepare()
                Log.d("Value","no show")
                Log.d("Value",full)
                database.child("PI_01_CONTROL").child("camera").setValue("0")

                handler.postDelayed(showSuccessToast,1500)

                view!!.findNavController().navigate(CaptureFragmentDirections.actionCaptureFragmentSelf(full))
            }

            val application = requireNotNull(this.activity).application
            val dataSource = SmartHomeDatabase.getInstance(application).doorDatabaseDao

            var door = Door()

            door.doorID = full
            door.year = year.toString()
            door.month = month.toString()
            door.day = day.toString()
            door.time = String.format("%02d",hour) + ":" + String.format("%02d",minute)

            dataSource.insert(door)

        }

        binding.btnAlert.setOnClickListener { v: View? ->
            val alertDialogBuilder = AlertDialog.Builder(this.context)

            val builder = AlertDialog.Builder(this.context)
            builder.setTitle("Alert")
            builder.setMessage("Trigger Alarm and call 911?")

            builder.setPositiveButton("Alarm only") { dialog, which ->
                Log.d("Value","YES YES YES YES")
            }

            builder.setNegativeButton("Alarm and call 911") { dialog, which ->
                Toast.makeText(this.context,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("Cancel") { dialog, which ->
                Toast.makeText(
                    this.context,
                    "Alert Cancelled", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = builder.create()
            alertDialog.show()

        }


        return binding.root

    }

    internal var showSuccessToast: Runnable = Runnable {
        val toast1 = Toast.makeText(context, "Successfully captured!", Toast.LENGTH_LONG)
        toast1.show()
    }


}

