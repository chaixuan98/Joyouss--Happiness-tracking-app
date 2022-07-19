package com.android.myapplication.todo

import android.provider.BaseColumns

object QuizContainer {
    class QuizTable : BaseColumns {
        companion object {

            val QUESTION_TABLE_NAME = "asses_question"
            val QUESTION_COLUMN = "question"
            val OPTION1_COLUMN = "option1"
            val OPTION2_COLUMN = "option2"
            val OPTION3_COLUMN = "option3"
            val OPTION4_COLUMN = "option4"
            val OPTION5_COLUMN = "option5"
            val ansOne= "ans1"
            val ansTwo= "ans2"
            val ansThree= "ans3"
            val ansFour= "ans4"
            val ansFive= "ans5"
        }


    }
}