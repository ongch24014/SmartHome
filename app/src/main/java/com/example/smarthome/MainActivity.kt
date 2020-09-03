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
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.smarthome.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

                var year:Int
                var month:Int
                var day:Int
                var hour:Int
                var minute:Int
                var second:Int
                var full:String
                second = (((Calendar.getInstance().get(Calendar.SECOND) / 10) + 1) * 10)
                second = second - 10

                year = Calendar.getInstance().get(Calendar.YEAR)
                month = Calendar.getInstance().get(Calendar.MONTH) + 1
                day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                minute = Calendar.getInstance().get(Calendar.MINUTE)

                if(second == 60){
                    second = 0
                    minute = minute + 1
                }

                var child1 = "PI_01_" + year + String.format("%02d",month) + String.format("%02d",day)
                var child2 = String.format("%02d",hour)
                var child3 = String.format("%02d",minute) + String.format("%02d",second)


                var database = FirebaseDatabase.getInstance().reference

                database.child(child1).child(child2).child(child3).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val commonResource = dataSnapshot.getValue(CommonResourcesData::class.java)
                        Log.d("Value",commonResource!!.ultra)
                        val ultra:String = commonResource!!.ultra

                        if (ultra.toInt() > 494){
                            val toast1 = Toast.makeText(applicationContext, "check check", Toast.LENGTH_LONG)
                            toast1.show()
                        }
                    }

                })


                if(yesno == true) {
//                    val i = Intent(AlarmClock.ACTION_SET_TIMER)
//                    i.putExtra(AlarmClock.EXTRA_MESSAGE, "DOOR OPENED!")
//                    i.putExtra(AlarmClock.EXTRA_LENGTH, 2)
//                    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
//                    startActivity(i)
//
//                    yesno = false;


//                    Timer().schedule(15000) {
//                        Looper.prepare()
//                        yesno = true;
//                    }

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
