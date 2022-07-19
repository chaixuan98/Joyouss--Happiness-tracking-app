package crudmoodstatus

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.activity_view_day_of_calendar.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ViewDayOfCalendarActivity : AppCompatActivity() {

    private var entryIDList = mutableListOf<Int>()
    private var entryDateList = mutableListOf<String>()
    private var entryTimeList = mutableListOf<String>()
    private var entryMoodStatusList = mutableListOf<Int>()
    private var entryActivityStatusList = mutableListOf<String>()
    private var onClickDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_day_of_calendar)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //get pick on date from previous activity
        onClickDate = intent.getStringExtra("onClickDate").toString()

        //read data from database
        val context = this
        val db = MoodHandler(context)
        val data = db.readData()
        var check = false

        //view data from database in recycler view by passing array
        for(i in 0 until data.size){
            if(data[i].entryDate == onClickDate) {
                addToList(
                    data[i].entryID,
                    data[i].entryDate,
                    data[i].entryTime,
                    data[i].entryMood,
                    data[i].entryActivityStatus
                )
                check = true
            }
        }
        if(check)requestAddingTxt.visibility= View.INVISIBLE

        cvDetRecyclerView.layoutManager = LinearLayoutManager(this)
        cvDetRecyclerView.adapter = MoodEntryAdapter(entryIDList,entryDateList,entryTimeList,entryMoodStatusList,entryActivityStatusList,context)

    }


    //create array for passing into recycler view
    private fun addToList(id:Int,date:String, time:String, moodStatus: Int, activityStatus:String){
        entryIDList.add(id)
        entryDateList.add(date)
        entryTimeList.add(time)
        entryMoodStatusList.add(moodStatus)
        entryActivityStatusList.add(activityStatus)
    }


}