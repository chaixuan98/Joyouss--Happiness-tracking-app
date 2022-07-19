package crudmoodstatus

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.activity_view_entry.*

class ViewEntryActivity : AppCompatActivity() {
    private var editedID = 0
    var editedDate = ""
    var editedTime = ""
    private var editedMood = 0
    private var editedStatus = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //get all entries from database
        val context = this
        val db = MoodHandler(context)
        val data = db.readData()

        //info : getIntent().getIntExtra() returns intent used to create the calling activity
        //       data.getIntExtra() returns intent used to launch the second activity
        //get on click entry index value
        val position = intent.getSerializableExtra("position").toString()
        var index = Integer.parseInt(position)

        //get index of onclick entry by using entryID
        for(i in 0 until data.size){
            if(data[i].entryID==index){
                index = i
            }
        }

        //show recorded date
        dateView.text = data[index].entryDate
        //show recorded time
        timeView.text = data[index].entryTime
        //show recorded emoticon
        when(data[index].entryMood) {
            1 -> {
                viewEmoticonSize(5)
                viewEmoticonDescription(
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.VISIBLE
                )
                //change layout color
                viewEntryBackground.setBackgroundColor(Color.parseColor("#4BBCF4"))
            }
            2 -> {
                viewEmoticonSize(4)
                viewEmoticonDescription(
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.VISIBLE,
                    View.INVISIBLE
                )
                //change layout color
                viewEntryBackground.setBackgroundColor(Color.parseColor("#61C0BF"))
            }
            3 -> {
                viewEmoticonSize(3)
                viewEmoticonDescription(
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.VISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE
                )
                //change layout color
                viewEntryBackground.setBackgroundColor(Color.parseColor("#BBDED6"))
            }
            4 -> {
                viewEmoticonSize(2)
                viewEmoticonDescription(
                    View.INVISIBLE,
                    View.VISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE
                )
                //change layout color
                viewEntryBackground.setBackgroundColor(Color.parseColor("#FFB6B9"))

            }
            5 -> {
                viewEmoticonSize(1)
                viewEmoticonDescription(
                    View.VISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE,
                    View.INVISIBLE
                )
                //change layout color
                viewEntryBackground.setBackgroundColor(Color.parseColor("#FAD0C9"))
            }

        }
        if(data[index].entryActivityStatus==""){
            emojiNoteView.text = " -"
        }else{
            emojiNoteView.text = data[index].entryActivityStatus
        }

        //when click SAVE button
        returnBut.setOnClickListener{
            finish()
        }


    }
    //set emoticon description visibility based on record
    // (joy,compassion,powerless,indifference,sadness)
    private fun viewEmoticonDescription(emo1:Int,emo2:Int,emo3:Int,emo4:Int,emo5:Int){
        viewEmoDes1.visibility = emo1
        viewEmoDes2.visibility = emo2
        viewEmoDes3.visibility = emo3
        viewEmoDes4.visibility = emo4
        viewEmoDes5.visibility = emo5
    }

    //zoom emoticon based on record
    private fun viewEmoticonSize(zoomEmoNum:Int) {

        //set all emoticon to default size
        viewEmoBut1.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        viewEmoBut2.layoutParams.width =  LinearLayout.LayoutParams.WRAP_CONTENT
        viewEmoBut3.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        viewEmoBut4.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        viewEmoBut5.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT

        //scale up selected emoticon
        when(zoomEmoNum) {
            1 -> viewEmoBut1.layoutParams.width = 1200
            2 -> viewEmoBut2.layoutParams.width = 1200
            3 -> viewEmoBut3.layoutParams.width = 1200
            4 -> viewEmoBut4.layoutParams.width = 1200
            5 -> viewEmoBut5.layoutParams.width = 1200
        }
        viewEmoBut1.requestLayout()
    }

}