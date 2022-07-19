package com.android.myapplication.todo

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_add_entry.*
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultDescription()

        btn_finish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, crudmoodstatus.MainActivity::class.java))
            finish()
        }

    }

    fun resultDescription(){

        var resultDb = ResultDBHelper(applicationContext)
        var db = resultDb.readableDatabase
        var rs = db.rawQuery("SELECT * FROM RESULT", null)


        var adapter = SimpleCursorAdapter(
                applicationContext, android.R.layout.simple_expandable_list_item_2, rs,
                arrayOf("MARK", "DATE"),
                intArrayOf(android.R.id.text1, android.R.id.text2), 0)

        listView.adapter = adapter

        btn_view_result.setOnClickListener {
            listView.visibility = View.VISIBLE
            adapter.notifyDataSetChanged()
        }

        val totalScore = intent.getIntExtra(QuizQuestionActivity.FINAL_SCORE, 0)

        var moodLevel = ""

        if (totalScore in 16..28){
            moodLevel = "Depressed"
        }
        if (totalScore in 29..40){
            moodLevel = "Unhappy"
        }
        if (totalScore in 41..52){
            moodLevel = "Neutral"
        }
        if (totalScore in 53..64){
            moodLevel = "Satisfied"
        }
        if (totalScore in 65..80){
            moodLevel = "Joy"
        }

        score.text = "Score: $totalScore ($moodLevel)"

        var formattedDate = ""
        //get date and time in format for API 21 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy h:mm:ss a")
            formattedDate = current.format(dateFormatter)
        } else {
            val date = Date()
            val dateFormatter = SimpleDateFormat("dd.MM.yyyy h:mm:ss a")
            formattedDate = dateFormatter.format(date)
        }


        var cv = ContentValues()
        cv.put("MARK", score.text.toString())
        cv.put("DATE", formattedDate)
        db.insert("RESULT", null, cv)


        if (totalScore in 16..28) {
            result_image.setImageResource(R.drawable.result_5)
            result_desc.text = "Cheer up! Life can’t be that bad!"
        }

        if (totalScore in 29..40) {
            result_image.setImageResource(R.drawable.result_4)
            result_desc.text = "Your happiness level is lower than average, cheer up!"
        }

        if (totalScore in 41..52) {
            result_image.setImageResource(R.drawable.result_3)
            result_desc.text = "Your happiness level is average, keep it up!"
        }

        if (totalScore in 53..64) {
            result_image.setImageResource(R.drawable.result_2)
            result_desc.text = "You are doing great, keep it up!"
        }

        if (totalScore in 65..80) {
            result_image.setImageResource(R.drawable.result_1)
            result_desc.text = "You are doing really fine, maintain it!"
        }

    }
}