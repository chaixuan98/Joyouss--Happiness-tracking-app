package crudmoodstatus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.R
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_view_calendar.*
import java.text.SimpleDateFormat
import java.util.*


class ViewCalendarActivity : AppCompatActivity() {

    private var entryIDList = mutableListOf<Int>()
    private var entryDateList = mutableListOf<Date>()
    private var entryTimeList = mutableListOf<String>()
    private var entryMoodStatusList = mutableListOf<Int>()
    private var entryActivityStatusList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_calendar)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //read data from database
        val context = this
        val db = MoodHandler(context)
        val data = db.readData()

        //view data from database into array
        for(i in 0 until data.size){
            addToList(data[i].entryID,conversionDate(data[i].entryDate), data[i].entryTime, data[i].entryMood,
                data[i].entryActivityStatus)
        }

        //adding events with icons
        val events = mutableListOf<EventDay>()
        for(i in 0 until data.size) {
            val cal = Calendar.getInstance()
            cal.set(splitStr(data[i].entryDate)[2].toInt(),splitStr(data[i].entryDate)[1].toInt()-1,splitStr(data[i].entryDate)[0].toInt())
            //specify event label color
            events.add(
                EventDay(
                    cal,
                    R.drawable.calendar_icon,
                    Color.parseColor("#9C27B0")
                )
            )
        }
        calendarView.setEvents(events)


        //clicks handling
        calendarView.setOnDayClickListener(object :
            OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                eventDay.calendar
                val intent = Intent(context,ViewDayOfCalendarActivity::class.java)
                //to pass any data to next activity
                intent.putExtra("onClickDate", getFormattedDate(eventDay.calendar))
                //start next activity
                startActivity(intent)
            }
        })
        val date = Date()
        val dateFormatter = SimpleDateFormat("MM")
        var currentMth = dateFormatter.format(date)


        generateGraph(data)

    }

    //create array for passing into recycler view
    private fun addToList(id:Int, date: Date, time: String, moodStatus: Int, activityStatus:String){
        entryIDList.add(id)
        entryDateList.add(date)
        entryTimeList.add(time)
        entryMoodStatusList.add(moodStatus)
        entryActivityStatusList.add(activityStatus)
    }
    private fun conversionDate(dateElement:String) : Date {
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
        return dateFormatter.parse(dateElement)
    }
    fun getFormattedDate(calendar: Calendar): String {
        val dateStr = calendar.time
        val simpleDateFormat =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return simpleDateFormat.format(dateStr)
    }
    private fun splitStr(dateStr: String):List<String>{
        val delimiter = "."
        return dateStr.split(delimiter)
    }
    private fun generateGraph(data: List<MoodEntry>){
        val date = Date()
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
        val currrentDate = dateFormatter.format(date)

        val xyList1 = mutableListOf<DataPoint>()
        val xyList2 = mutableListOf<DataPoint>()
        val xyList3 = mutableListOf<DataPoint>()
        val xyList4 = mutableListOf<DataPoint>()
        val xyList5 = mutableListOf<DataPoint>()
        var emo1 = 0.0
        var emo2 = 0.0
        var emo3 = 0.0
        var emo4 = 0.0
        var emo5 = 0.0

        //view data from database in recycler view by passing array
        for(element in data) {
            if(currrentDate == element.entryDate){
                when (element.entryMood) {
                    1 -> emo5++
                    2 -> emo4++
                    3 -> emo3++
                    4 -> emo2++
                    else -> emo1++
                }
            }
        }
        xyList1.add(DataPoint(1.0,emo1))
        xyList2.add(DataPoint(2.0,emo2))
        xyList3.add(DataPoint(3.0,emo3))
        xyList4.add(DataPoint(4.0,emo4))
        xyList5.add(DataPoint(5.0,emo5))
        xyList1.sortBy{ it.x}
        xyList2.sortBy{ it.x}
        xyList3.sortBy{ it.x}
        xyList4.sortBy{ it.x}
        xyList5.sortBy{ it.x}
        val series1 = BarGraphSeries(xyList1.toTypedArray())
        val series2 = BarGraphSeries(xyList2.toTypedArray())
        val series3 = BarGraphSeries(xyList3.toTypedArray())
        val series4 = BarGraphSeries(xyList4.toTypedArray())
        val series5 = BarGraphSeries(xyList5.toTypedArray())
        series1.title = "joy"
        series2.title = "care"
        series3.title = "powerless"
        series4.title = "uninterested"
        series5.title = "sad"
        barChart.removeAllSeries()
        barChart.addSeries(series1)
        barChart.addSeries(series2)
        barChart.addSeries(series3)
        barChart.addSeries(series4)
        barChart.addSeries(series5)

        series1.isDrawValuesOnTop = true
        series1.valuesOnTopColor = Color.BLACK
        series1.color = Color.BLUE

        series2.isDrawValuesOnTop = true
        series2.valuesOnTopColor = Color.BLACK
        series2.color = Color.CYAN

        series3.isDrawValuesOnTop = true
        series3.valuesOnTopColor = Color.BLACK
        series3.color = Color.GREEN

        series4.isDrawValuesOnTop = true
        series4.valuesOnTopColor = Color.BLACK
        series4.color = Color.MAGENTA

        series5.isDrawValuesOnTop = true
        series5.valuesOnTopColor = Color.BLACK
        series5.color = Color.YELLOW


        //padding of graph
        barChart.gridLabelRenderer.padding= 120
        barChart.gridLabelRenderer.gridStyle = GridLabelRenderer.GridStyle.NONE
        barChart.gridLabelRenderer.isVerticalLabelsVisible = false
        barChart.gridLabelRenderer.isHorizontalLabelsVisible = false
        // Display the legend.
        barChart.legendRenderer.isVisible = true;
    }

}
