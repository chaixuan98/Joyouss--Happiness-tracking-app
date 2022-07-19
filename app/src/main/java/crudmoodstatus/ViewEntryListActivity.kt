package crudmoodstatus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.activity_view_entry_list.*
import kotlinx.android.synthetic.main.activity_view_entry_list.requestAddingTxt

//recycler view of mood entries
class ViewEntryListActivity : AppCompatActivity() {

    private var entryIDList = mutableListOf<Int>()
    private var entryDateList = mutableListOf<String>()
    private var entryTimeList = mutableListOf<String>()
    private var entryMoodStatusList = mutableListOf<Int>()
    private var entryActivityStatusList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry_list)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //read data from database
        val context = this
        val db = MoodHandler(context)
        val data = db.readData()

        //view data from database in recycler view by passing array
        for(i in 0 until data.size){
            addToList(data[i].entryID,
                data[i].entryDate, data[i].entryTime, data[i].entryMood, data[i].entryActivityStatus)
        }
        if(data.size!=0)requestAddingTxt.visibility= View.INVISIBLE
        cvRecyclerView.layoutManager = LinearLayoutManager(this)
        cvRecyclerView.adapter = MoodEntryAdapter(entryIDList,entryDateList,entryTimeList,entryMoodStatusList,entryActivityStatusList,context)

        btnCalendar.setOnClickListener{
            val i = Intent(applicationContext, ViewCalendarActivity::class.java)
            startActivity(i)
        }

        btnStats.setOnClickListener{
            val i = Intent(applicationContext, crudmoodstatus.ShowGraphActivity::class.java)
            startActivity(i)
        }
    }

    //create array for passing into recycler view
    private fun addToList(id:Int,date:String, time:String, moodStatus: Int, activityStatus:String){
        entryIDList.add(id)
        entryDateList.add(date)
        entryTimeList.add(time)
        entryMoodStatusList.add(moodStatus)
        entryActivityStatusList.add(activityStatus)
    }


    private fun initializeList(){
        try {
            entryIDList.clear()
            entryDateList.clear()
            entryTimeList.clear()
            entryMoodStatusList.clear()
            entryActivityStatusList.clear()
        }catch (e:NullPointerException){

        }

    }


}