package activity

//set reminder when user clicks from home menu

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
import kotlinx.android.synthetic.main.alarmhome.*
import java.util.*

class HomeSetReminderActivity : AppCompatActivity(){

    lateinit var am :AlarmManager
    lateinit var i :Intent
    lateinit var pi:PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarmhome)

        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        i =Intent(this@HomeSetReminderActivity, BcastRcver::class.java)
        pi= PendingIntent.getBroadcast(this@HomeSetReminderActivity,0,i,PendingIntent.FLAG_UPDATE_CURRENT)

        am.cancel(pi)

        val timePicker = findViewById<TimePicker>(R.id.timepicker)
        val btnSet = findViewById<Button>(R.id.set)
        var mHour : Int? = null
        var mMin : Int? = null

        //timepicker
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            mHour = hourOfDay
            mMin = minute
        }

        //if user clicks to set reminder
        btnSet.setOnClickListener{

            var calendar_alarm = Calendar.getInstance()
            var calendar_now = Calendar.getInstance()

            val nonNullableHour: Int = mHour!!
            val nonNullableMin: Int = mMin!!

            calendar_alarm.set(Calendar.HOUR_OF_DAY, nonNullableHour)
            calendar_alarm.set(Calendar.MINUTE, nonNullableMin)
            calendar_alarm.set(Calendar.SECOND, 0)

            //increase date by one
            if(calendar_alarm.before(calendar_now)) {
                calendar_alarm.add(Calendar.DATE, 1)
            }

            //set alarm
            am.set(AlarmManager.RTC_WAKEUP, calendar_alarm.getTimeInMillis(), pi)
            Toast.makeText(this@HomeSetReminderActivity, "Reminder set successfully", Toast.LENGTH_SHORT).show()

            //navigate back to home menu
            startActivity(Intent(this@HomeSetReminderActivity, HomeActivity::class.java))
            finish()
        }
    }
}