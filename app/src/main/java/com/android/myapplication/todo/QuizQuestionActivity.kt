package com.android.myapplication.todo

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import java.util.ArrayList

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: List<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mScore: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val questionDb = DatabaseHelper(this)
        mQuestionsList = questionDb.questionSet

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_option_five.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four, 4)
            }
            R.id.tv_option_five ->{
                selectedOptionView(tv_option_five, 5)
            }
            R.id.btn_submit ->{

                if (mSelectedOptionPosition == 0) {
                    Toast.makeText(this@QuizQuestionActivity, "Select An Option", Toast.LENGTH_SHORT).show()

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()

                        }
                        else -> {
                            val intent =
                                Intent(this@QuizQuestionActivity, ResultActivity::class.java)
                            intent.putExtra(FINAL_SCORE, mScore)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else {
                    val question = mQuestionsList?.get(mCurrentPosition -1 )

                    // This is to sum up the score with different options
                    if (question!!.getmAnsOpt1() == mSelectedOptionPosition) {
                        mScore+=5
                    }
                    if (question!!.getmAnsOpt2() == mSelectedOptionPosition) {
                        mScore+=4
                    }
                    if (question!!.getmAnsOpt3() == mSelectedOptionPosition) {
                        mScore+=3
                    }
                    if (question!!.getmAnsOpt4() == mSelectedOptionPosition) {
                        mScore+=2
                    }
                    if (question!!.getmAnsOpt5() == mSelectedOptionPosition) {
                        mScore+=1
                    }
                    // END

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }

                    mCurrentPosition++
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun setQuestion() {

        val question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {

            btn_submit.text = "SUBMIT"}



        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        tv_question.text = question.getmQuestion()
        tv_option_one.text = question.getmOption1()
        tv_option_two.text = question.getmOption2()
        tv_option_three.text = question.getmOption3()
        tv_option_four.text = question.getmOption4()
        tv_option_five.text = question.getmOption5()
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionActivity,
            R.drawable.selected_option_border_bg
        )
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)
        options.add(4, tv_option_five)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    companion object {

        val FINAL_SCORE = "FinalScore"

    }

}

