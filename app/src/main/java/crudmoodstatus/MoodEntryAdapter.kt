package crudmoodstatus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.todo.R


//adapter between recycle view (activity_view_entry_list) and single layout (entry_layout)
class MoodEntryAdapter(private var entryid: List<Int>, private var date: List<String>, private var time: List<String>,
                      private var moodStatus: List<Int>, private var activityStatus: List<String>, private var context: Context):
    RecyclerView.Adapter<MoodEntryAdapter.ViewHolder>(){

    //------single layout class
    //get id from layout (entry_layout)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val entryDate : TextView = itemView.findViewById(R.id.cvDate)
        val entryTime : TextView = itemView.findViewById(R.id.cvTime)
        val entryMoodStatus : TextView = itemView.findViewById(R.id.cvMoodStatus)
        val entryMoodPic : ImageView = itemView.findViewById(R.id.cvImage)
        val entryBackground : RelativeLayout = itemView.findViewById(R.id.cvWrapper)

        //set listener for clicks on layout
        init{
            itemView.setOnClickListener{v:View ->
                val position: Int = adapterPosition
            }
        }

    }

    //------recycle view class
    //create view holder in recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.entry_layout,parent,false)
        return ViewHolder(v)
    }

    //calculate holder in recycler view
    override fun getItemCount(): Int {
        return entryid.size
    }

    //bind to view holder in recycler view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.entryDate.text = date[position]
        holder.entryTime.text = time[position]
        holder.entryMoodStatus.text = when(moodStatus[position]){
            1 -> "SAD"
            2 -> "UNINTERESTED"
            3 -> "POWERLESS"
            4 -> "CARE"
            else -> "JOY"
        }
        //holder.entryActivityStatus.text = activityStatus[position]
        holder.entryMoodPic.setImageResource(when(moodStatus[position]) {
            1 -> R.drawable.sadness
            2 -> R.drawable.indifference
            3 -> R.drawable.powerless
            4 -> R.drawable.compassion
            else -> R.drawable.joy
        })
        holder.entryBackground.setBackgroundColor(when(moodStatus[position]){
            1 -> Color.parseColor("#4BBCF4")
            2 -> Color.parseColor("#61C0BF")
            3 -> Color.parseColor("#BBDED6")
            4 -> Color.parseColor("#FFB6B9")
            else -> Color.parseColor("#FAD0C9")
        })

        holder.itemView.setOnClickListener{v:View ->
            //start next activity/
            val intent = Intent(context, ViewEntryActivity::class.java)
            //to pass any data to next activity
            intent.putExtra("position", entryid[position])
            ///(context as Activity).startActivityForResult(intent, 1002)
            context.startActivity(intent)

        }

    }

}