package activity

//screen for set alarm reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.HomeActivity
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.alarm.*
import java.util.*

class SetReminderActivity : AppCompatActivity(){

    //declaring variables
    lateinit var am :AlarmManager
    lateinit var i :Intent
    lateinit var pi:PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm)

        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        i =Intent(this@SetReminderActivity, BcastRcver::class.java)
        //broadcast i in this activity context
        pi= PendingIntent.getBroadcast(this@SetReminderActivity,0,i,PendingIntent.FLAG_UPDATE_CURRENT)

        if (pi != null && am != null) {
            am.cancel(pi) //reset reminder if any reminder exists
        }

        val timePicker = findViewById<TimePicker>(R.id.timepicker)
        val btnSkip = findViewById<Button>(R.id.skip)
        val btnSet = findViewById<Button>(R.id.set)
        var mHour : Int? = null
        var mMin : Int? = null

        //timepicker, retrieve time
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            mHour = hourOfDay
            mMin = minute
        }

        //if user does not want to set reminder
        btnSkip.setOnClickListener{
            startActivity(Intent(this@SetReminderActivity, HomeActivity::class.java))
            finish()
        }

        //if user sets reminder
        btnSet.setOnClickListener{
            //declare two calendar to get the correct date
            var calendar_alarm = Calendar.getInstance()
            var calendar_now = Calendar.getInstance()

            val nonNullableHour: Int = mHour!!
            val nonNullableMin: Int = mMin!!

            //set hour, minute and second
            calendar_alarm.set(Calendar.HOUR_OF_DAY, nonNullableHour)
            calendar_alarm.set(Calendar.MINUTE, nonNullableMin)
            calendar_alarm.set(Calendar.SECOND, 0)

            //increase date by 1
            if(calendar_alarm.before(calendar_now)) {
                calendar_alarm.add(Calendar.DATE, 1)
            }

            //set alarm to go off at calendar_alarm time and perform pi
            am.set(AlarmManager.RTC_WAKEUP, calendar_alarm.getTimeInMillis(), pi)
            //message toast telling user that reminder alarm is set
            Toast.makeText(this@SetReminderActivity, "Reminder set successfully", Toast.LENGTH_SHORT).show()

            //return to home menu
            startActivity(Intent(this@SetReminderActivity, HomeActivity::class.java))
            finish()
        }
    }

}