package com.android.myapplication.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        startButton.setOnClickListener {
            val i = Intent(applicationContext, QuizQuestionActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}