package com.example.smarthome.LightModule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smarthome.R

/**
 * A simple [Fragment] subclass.
 */
class SetTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_time, container, false)
    }

//    val timePicker = CmtpDialogFragment.newInstance()
//
//    timePicker.setInitialTime12(5, 15, CmtpTime12.PmAm.PM)
//    timePicker.setOnTime12PickedListener { time12 ->
//
//        val cal:Calendar = Calendar.getInstance()
//        val timeSetListener: TimePickerDialog.OnTimeSetListener =
//            TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
//                cal.set(Calendar.HOUR_OF_DAY, hour)
//                cal.set(Calendar.MINUTE, minute)
//            }
//
//        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//            .setAction("Action", null)
//            .show()
//    }
//    timePicker.show(activity!!.supportFragmentManager, "TimePickerTag")

}
