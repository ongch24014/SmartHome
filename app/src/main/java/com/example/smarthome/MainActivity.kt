package com.example.smarthome

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.smarthome.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var sharedPreferences: SharedPreferences

    companion object {
        var bellRing = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        bellRing = sharedPreferences.getString("bellRing","").toString()

        var yesno:Boolean = true;

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {

                handler.postDelayed(this, 10000)//1 sec delay
//                val toast1 = Toast.makeText(applicationContext, "check check", Toast.LENGTH_LONG)
//                toast1.show()
                if(yesno == true) {
//                    val i = Intent(AlarmClock.ACTION_SET_TIMER)
//                    i.putExtra(AlarmClock.EXTRA_MESSAGE, "DOOR OPENED!")
//                    i.putExtra(AlarmClock.EXTRA_LENGTH, 2)
//                    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                    startActivity(i)
//
//                    yesno = false;


                    Timer().schedule(15000) {
                        Looper.prepare()
                        yesno = true;
                    }

                }

                else{


                }

            }
        }, 10000)

    }

    override fun onResume() {
        bellRing = sharedPreferences.getString("bellRing","").toString()

        super.onResume()
    }

    override fun onPause() {
        with(sharedPreferences.edit()){
            putString("bellRing", bellRing)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        with(sharedPreferences.edit()){
            putString("bellRing", bellRing)
            commit()
        }
        super.onStop()
    }


}
