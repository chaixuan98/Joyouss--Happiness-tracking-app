package com.android.myapplication.todo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
//import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.IMAGE_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.OPTION1_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.OPTION2_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.OPTION3_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.OPTION4_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.OPTION5_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.QUESTION_COLUMN
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.QUESTION_TABLE_NAME
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.ansFive
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.ansFour
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.ansOne
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.ansThree
import com.android.myapplication.todo.QuizContainer.QuizTable.Companion.ansTwo
import java.util.ArrayList

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null

    val questionSet: List<Question>
        get() {

            val questionSetsList = ArrayList<Question>()

            db = readableDatabase

            val c = db!!.rawQuery("SELECT * FROM $QUESTION_TABLE_NAME", null)

            if (c.moveToFirst()) {
                do {
                    val question = Question()
                    question.setmQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN)))
                    question.setmOption1(c.getString(c.getColumnIndex(OPTION1_COLUMN)))
                    question.setmOption2(c.getString(c.getColumnIndex(OPTION2_COLUMN)))
                    question.setmOption3(c.getString(c.getColumnIndex(OPTION3_COLUMN)))
                    question.setmOption4(c.getString(c.getColumnIndex(OPTION4_COLUMN)))
                    question.setmOption5(c.getString(c.getColumnIndex(OPTION5_COLUMN)))
                    question.setmAnsOpt1(c.getInt(c.getColumnIndex(ansOne)))
                    question.setmAnsOpt2(c.getInt(c.getColumnIndex(ansTwo)))
                    question.setmAnsOpt3(c.getInt(c.getColumnIndex(ansThree)))
                    question.setmAnsOpt4(c.getInt(c.getColumnIndex(ansFour)))
                    question.setmAnsOpt5(c.getInt(c.getColumnIndex(ansFive)))
                    questionSetsList.add(question)
                } while (c.moveToNext())

            }
            c.close()
            return questionSetsList
        }

    override fun onCreate(db: SQLiteDatabase) {
        this.db = db

        val QB_TABLE = "CREATE TABLE " +
                QUESTION_TABLE_NAME + " ( " +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTION_COLUMN + " TEXT, " +
                OPTION1_COLUMN + " TEXT, " +
                OPTION2_COLUMN + " TEXT, " +
                OPTION3_COLUMN + " TEXT, " +
                OPTION4_COLUMN + " TEXT, " +
                OPTION5_COLUMN + " TEXT, " +
                ansOne + " INTEGER, " +
                ansTwo + " INTEGER, " +
                ansThree + " INTEGER, " +
                ansFour + " INTEGER, " +
                ansFive + " INTEGER " +
                " )"

        db.execSQL(QB_TABLE)

        GenerateQuestionFunction()


    }

    private fun GenerateQuestionFunction() {
        val q1 = Question(
            "I take every opportunity to play, laugh, and have a good time.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5)
        addQuestion(q1)
        val q2 = Question(
            "I usually have a holiday at least once a year.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5)
        addQuestion(q2)
        val q3 = Question(
            "I get pleasure from lots of different things – art nature, sport, friends.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5)
        addQuestion(q3)
        val q4 = Question(
            "Sometimes I get really enthusiastic about things.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5)
        addQuestion(q4)
        val q5 =Question("I have the things in life that I think are important.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5)
        addQuestion(q5)

        val q6 = Question(
            "I have a positive image of myself.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q6)

        val q7 = Question(
            "I am grateful for what I have, and appreciate it.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q7)

        val q8 = Question(
            "I don’t often feel jealous or envious of other people.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q8)

        val q9 = Question(
            "I sleep well and wake up feeling ready for a new day.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q9)

        val q10 = Question(
            "I keep fit and I take care of myself.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q10)

        val q11 = Question(
            "I never feel stressed when I have a lot of things to do.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q11)

        val q12 = Question(
            " I don’t feel afraid or depressed.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q12)

        val q13 = Question(
            " I have close friends and people I share interests with.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q13)

        val q14 = Question(
            " I get a lot of satisfaction from my work/study.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q14)

        val q15 = Question(
            " My life makes a difference to other people.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q15)

        val q16 = Question(
            " I try to help other people.",
            "Strongly Agree",
            "Agree",
            "Neutral",
            "Disagree",
            "Strongly Disagree",
            1, 2, 3, 4, 5
        )
        addQuestion(q16)
    }

    private fun addQuestion(qb: Question) {
        val contentValues = ContentValues()
        contentValues.put(QUESTION_COLUMN, qb.getmQuestion())
        contentValues.put(OPTION1_COLUMN, qb.getmOption1())
        contentValues.put(OPTION2_COLUMN, qb.getmOption2())
        contentValues.put(OPTION3_COLUMN, qb.getmOption3())
        contentValues.put(OPTION4_COLUMN, qb.getmOption4())
        contentValues.put(OPTION5_COLUMN, qb.getmOption5())
        contentValues.put(ansOne, qb.getmAnsOpt1())
        contentValues.put(ansTwo, qb.getmAnsOpt2())
        contentValues.put(ansThree, qb.getmAnsOpt3())
        contentValues.put(ansFour, qb.getmAnsOpt4())
        contentValues.put(ansFive, qb.getmAnsOpt5())
        db!!.insert(QUESTION_TABLE_NAME, null, contentValues)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        private val DATABASE_NAME = "Quiz.db"
        private val DATABASE_VERSION = 1
    }

}
