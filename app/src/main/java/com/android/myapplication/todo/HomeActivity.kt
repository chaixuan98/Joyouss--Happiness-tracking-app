package com.android.myapplication.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        menu_mood.setOnClickListener {
            val i = Intent(applicationContext, crudmoodstatus.MainActivity::class.java)
            startActivity(i)
        }

        menu_entries_list.setOnClickListener {
            val i = Intent(applicationContext, crudmoodstatus.ViewEntryListActivity::class.java)
            startActivity(i)
        }

        menu_note_reminder.setOnClickListener {
            val i = Intent(applicationContext, activity.HomeSetReminderActivity::class.java)
            startActivity(i)
        }

        menu_activities.setOnClickListener {
            val i = Intent(applicationContext, activity.MainActivity::class.java)
            startActivity(i)
        }

        menu_test.setOnClickListener {
            val i = Intent(applicationContext, QuizActivity::class.java)
            startActivity(i)
        }

        menu_resources.setOnClickListener {
            val i = Intent(applicationContext, ResourcesActivity::class.java)
            startActivity(i)
        }


    }
}