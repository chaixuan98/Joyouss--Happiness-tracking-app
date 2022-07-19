package crudmoodstatus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.HomeActivity
import com.android.myapplication.todo.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlinx.android.synthetic.main.activity_add_entry.*

//---add entry
//select emoticon for the first time/notification/menu
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        //hide header of application
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        val context = this
        val formattedTime: String
        val formattedDate: String
        var entryMood = 5
        var activityStatus = ""

        //get date and time in format for API 21 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
            formattedTime = current.format(timeFormatter)
            val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            formattedDate = current.format(dateFormatter)
        } else {
            val date = Date()
            val timeFormatter = SimpleDateFormat("hh:mm a")
            formattedTime = timeFormatter.format(date)
            val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
            formattedDate = dateFormatter.format(date)
        }

        //set current date and time on gui
        addTime.text = formattedTime
        addDate.text = formattedDate

        //when select emoji 1 -JOY
        addEmoji1.setOnClickListener{
            setEmoticonSize(1)
            setEmoticonDescription(View.VISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE)
            //set entry mood value to store
            entryMood = 5
        }

        //when select emoji 2 -
        addEmoji2.setOnClickListener{
            setEmoticonSize(2)
            setEmoticonDescription(View.INVISIBLE,View.VISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE)
            //set entry mood value to store
            entryMood = 4
        }

        //when select emoji 3
        addEmoji3.setOnClickListener{
            setEmoticonSize(3)
            setEmoticonDescription(View.INVISIBLE,View.INVISIBLE,View.VISIBLE,View.INVISIBLE,View.INVISIBLE)
            //set entry mood value to store
            entryMood = 3
        }

        //when select emoji 4
        addEmoji4.setOnClickListener{
            setEmoticonSize(4)
            setEmoticonDescription(View.INVISIBLE,View.INVISIBLE,View.INVISIBLE,View.VISIBLE,View.INVISIBLE)
            //set entry mood value to store
            entryMood = 2
        }

        //when select emoji 5
        addEmoji5.setOnClickListener{
            setEmoticonSize(5)
            setEmoticonDescription(View.INVISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE,View.VISIBLE)
            //set entry mood value to store
            entryMood = 1
        }

        cancelEntry.setOnClickListener {
            startActivity(Intent(this@MainActivity, activity.SetReminderActivity::class.java))
            finish()
        }

        //when click OK button
        addEntryBut.setOnClickListener {
            activityStatus = addActivityStatus.text.toString()
            //save into database
            val moodEntry = MoodEntry(formattedTime
                ,formattedDate
                ,entryMood
                ,activityStatus)
            val db = MoodHandler(context)
            db.insertData(moodEntry)

            if(entryMood == 3 || entryMood ==2 || entryMood ==1){
                startActivity(Intent(this@MainActivity, activity.SuggestActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this@MainActivity, activity.SetReminderActivity::class.java))
                finish()
            }

        }


    }

    //zoom emoticon when click onto it
    private fun setEmoticonSize(zoomEmoNum:Int) {

        //set all emoticon to default size
        addEmoji1.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        addEmoji2.layoutParams.width =  LinearLayout.LayoutParams.WRAP_CONTENT
        addEmoji3.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        addEmoji4.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        addEmoji5.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT

        //scale up selected emoticon
        when(zoomEmoNum) {
            1 -> addEmoji1.layoutParams.width = 1200
            2 -> addEmoji2.layoutParams.width = 1200
            3 -> addEmoji3.layoutParams.width = 1200
            4 -> addEmoji4.layoutParams.width = 1200
            5 -> addEmoji5.layoutParams.width = 1200
        }
        addEmoji1.requestLayout()
    }


    //set visibility of emoticon description(joy,care,powerless,indifference,sadness)
    private fun setEmoticonDescription(emo1:Int,emo2:Int,emo3:Int,emo4:Int,emo5:Int){
        addEmoDes1.visibility = emo1
        addEmoDes2.visibility = emo2
        addEmoDes3.visibility = emo3
        addEmoDes4.visibility = emo4
        addEmoDes5.visibility = emo5
    }
}