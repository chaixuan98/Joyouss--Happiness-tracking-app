package crudmoodstatus

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.R
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.jjoe64.graphview.series.OnDataPointTapListener
import kotlinx.android.synthetic.main.activity_show_graph.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ShowGraphActivity : AppCompatActivity() {

    companion object {
        var unlockStatus = false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_graph)


        //hide header of application
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //get current month and year string
        val current = LocalDateTime.now()
        val monthYearFormatter = DateTimeFormatter.ofPattern("MM.yyyy")
        val currentMonthYear = current.format(monthYearFormatter)

        //read data from database
        val context = this
        val db = MoodHandler(context)
        val data = db.readData()
        val xyList = mutableListOf<DataPoint>()
        var xyArr= arrayOf<DataPoint>()
        val checkCounter = mutableListOf<String>()
        var checkBoo = false

        for(i in 0 until data.size){
            //get a list of data point for current month
            if(currentMonthYear in data[i].entryDate) {
                xyList.add(DataPoint(conversion(data[i].entryDate), data[i].entryMood.toDouble()))
                checkCounter.add(splitStr(data[i].entryDate)[0])
            }

        }
        val sizeCheck = checkCounter.groupingBy { it }.eachCount()
        if(sizeCheck.size>2){
            checkBoo = true
            if(!unlockStatus){
                unlockStatus = true

            }

        }

        xyList.sortBy{ it.x}//sorting list of data point in asc
        xyArr = xyList.toTypedArray()//change list to array type data point
        var series = LineGraphSeries(xyArr)//create line graph

        // styling series for line graph
        series.title = "Mood Entry"
        series.color = Color.BLUE
        series.isDrawDataPoints = true
        series.dataPointsRadius = 10f
        series.thickness = 8

        //add data point to graph
        lineChart.addSeries(series)

        //set date label as x-axis
        lineChart.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(this)
        //set view size of y axis
        lineChart.viewport.setMinY(0.0)
        lineChart.viewport.setMaxY(5.0)
        lineChart.viewport.isYAxisBoundsManual = true
        //activate horizontal zooming and scrolling
        lineChart.viewport.isScalable = true
        //padding of graph
        lineChart.gridLabelRenderer.padding= 80
        //Use dates as labels, the human rounding to nice readable numbers is not necessary
        lineChart.gridLabelRenderer.setHumanRounding(false)
        //set x-axis & y-axis grid range
        lineChart.gridLabelRenderer.numHorizontalLabels = 5
        lineChart.gridLabelRenderer.numVerticalLabels = 6
        //set  x-axis & y-axis title
        lineChart.gridLabelRenderer.horizontalAxisTitle = "Day of Current Month"
        lineChart.gridLabelRenderer.verticalAxisTitle = "Mood Status"
        //set x-axis label format
        setLineGraphLabel(SimpleDateFormat("dd"))
        //set data point on tap listener
        setOnTapDtPtListener(series)


        if(unlockStatus){
            StatisticSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                // The switch is enabled/checked
                if (isChecked) {
                    //set description of graph shown
                    swtDesc.text = "Display mood status versus month/year for all the entries."
                    //clear line graph
                    lineChart.removeSeries(series)
                    //clear list
                    xyList.clear()
                    //get data point of all entries
                    for(i in 0 until data.size){
                        xyList.add(
                            DataPoint(
                                conversion(data[i].entryDate),
                                data[i].entryMood.toDouble()
                            )
                        )

                    }

                    xyList.sortBy{ it.x}//sorting list of data point in asc
                    xyArr = xyList.toTypedArray()//change list to array type data point
                    series = LineGraphSeries(xyArr)//create line graph

                    // styling series for line graph
                    series.title = "Mood Entry"
                    series.color = Color.GREEN
                    series.isDrawDataPoints = true
                    series.dataPointsRadius = 10f
                    series.thickness = 8
                    //set data point on tap listener
                    setOnTapDtPtListener(series)
                    //set x and y axis title
                    lineChart.gridLabelRenderer.horizontalAxisTitle = "Month/Year"
                    lineChart.gridLabelRenderer.verticalAxisTitle = "Mood Status"
                    //set x axis label
                    setLineGraphLabel(SimpleDateFormat("MM/yy"))

                    //add data point to graph
                    lineChart.addSeries(series)

                } else { // The switch is disabled
                    //set description of graph shown
                    swtDesc.text = "Display mood status versus date for this current month's entries."
                    //clear line graph
                    lineChart.removeSeries(series)
                    //clear list
                    xyList.clear()
                    //get data point of current month
                    for(i in 0 until data.size){
                        if(currentMonthYear in data[i].entryDate) {
                            xyList.add(
                                DataPoint(
                                    conversion(data[i].entryDate),
                                    data[i].entryMood.toDouble()
                                )
                            )
                        }
                    }
                    xyList.sortBy{ it.x}//sorting list of data point in asc
                    xyArr = xyList.toTypedArray()//change list to array type data point
                    series = LineGraphSeries(xyArr)//create line graph

                    // styling series for line graph
                    series.title = "Mood Entry"
                    series.color = Color.BLUE
                    series.isDrawDataPoints = true
                    series.dataPointsRadius = 10f
                    series.thickness = 8
                    //set data point on tap listener
                    setOnTapDtPtListener(series)
                    //set x and y axis title
                    lineChart.gridLabelRenderer.horizontalAxisTitle = "Day of Current Month"
                    lineChart.gridLabelRenderer.verticalAxisTitle = "Mood Status"
                    //set x axis label
                    setLineGraphLabel(SimpleDateFormat("dd"))

                    //add data point to graph
                    lineChart.addSeries(series)
                }
            })}else{

            StatisticSwitch.setOnClickListener {
                Toast.makeText(
                    this,
                    "Please enter mood for at least 3 days in a month to unlock this function !",
                    Toast.LENGTH_SHORT
                ).show()
                StatisticSwitch.isChecked = false
            }


        }


    }
    //date string to date object
    private fun conversion(dateElement:String) :Date{
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy")
        return dateFormatter.parse(dateElement)
    }
    //date object to date string
    private fun getDateString(dateStr: Double): String {
        val simpleDateFormat =
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return simpleDateFormat.format(dateStr)
    }
    //set graph x-axis & y-axis label
    private fun setLineGraphLabel(fmt:SimpleDateFormat){
        lineChart.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    return fmt.format( Date(value.toLong()))
                }else if(value==0.0){
                    return "No entry"
                }else if(value==1.0) {
                    return "Sad"
                }else if(value==2.0) {
                    return "Uninterested"
                }else if(value==3.0) {
                    return "Powerless"
                }else if(value==4.0) {
                    return "Care"
                }else{
                    return "Joy"
                }
            }
        }
    }
    //set on tap data point listener
    private fun setOnTapDtPtListener(dt:LineGraphSeries<DataPoint>){
        dt.setOnDataPointTapListener(OnDataPointTapListener { dt, dataPoint ->

            var moodStatus = ""
            moodStatus = when(dataPoint.y){
                1.0 -> "Sad"
                2.0 -> "Uninterested"
                3.0 -> "Powerless"
                4.0 -> "Care"
                else -> "Joy"
            }

            Toast.makeText(
                this,
                "Data Point clicked: " + getDateString(dataPoint.x) + " , " + moodStatus,
                Toast.LENGTH_SHORT
            ).show()
        })
    }
    private fun splitStr(dateStr: String):List<String>{
        val delimiter = "."
        return dateStr.split(delimiter)
    }
}