package com.example.smarthome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.smarthome.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {

                handler.postDelayed(this, 10000)//1 sec delay
//                val toast1 = Toast.makeText(applicationContext, "check check", Toast.LENGTH_LONG)
//                toast1.show()
            }
        }, 10000)

    }
}
