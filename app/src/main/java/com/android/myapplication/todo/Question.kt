package com.android.myapplication.todo

class Question {

    private var mQuestion: String? = null
    private var mOption1: String? = null
    private var mOption2: String? = null
    private var mOption3: String? = null
    private var mOption4: String? = null
    private var mOption5: String? = null
    private var mAnsOpt1: Int = 0
    private var mAnsOpt2: Int = 0
    private var mAnsOpt3: Int = 0
    private var mAnsOpt4: Int = 0
    private var mAnsOpt5: Int = 0



    constructor() {}

    constructor(mQuestion: String, mOption1: String, mOption2: String, mOption3: String, mOption4: String, mOption5: String,
                mAnsOpt1: Int, mAnsOpt2: Int, mAnsOpt3: Int, mAnsOpt4: Int, mAnsOpt5: Int) {
        this.mQuestion = mQuestion
        this.mOption1 = mOption1
        this.mOption2 = mOption2
        this.mOption3 = mOption3
        this.mOption4 = mOption4
        this.mOption5 = mOption5
        this.mAnsOpt1 = mAnsOpt1
        this.mAnsOpt2 = mAnsOpt2
        this.mAnsOpt3 = mAnsOpt3
        this.mAnsOpt4 = mAnsOpt4
        this.mAnsOpt5 = mAnsOpt5
    }
    //Question
    fun getmQuestion(): String? {
        return mQuestion
    }

    fun setmQuestion(mQuestion: String) {
        this.mQuestion = mQuestion
    }


    //Option 1
    fun getmOption1(): String? {
        return mOption1
    }

    fun setmOption1(mOption1: String) {
        this.mOption1 = mOption1
    }

    //Option 2
    fun getmOption2(): String? {
        return mOption2
    }

    fun setmOption2(mOption2: String) {
        this.mOption2 = mOption2
    }

    //Option 3
    fun getmOption3(): String? {
        return mOption3
    }

    fun setmOption3(mOption3: String) {
        this.mOption3 = mOption3
    }

    //Option 4
    fun getmOption4(): String? {
        return mOption4
    }

    fun setmOption4(mOption4: String) {
        this.mOption4 = mOption4
    }

    //Option 5
    fun getmOption5(): String? {
        return mOption5
    }

    fun setmOption5(mOption5: String) {
        this.mOption5 = mOption5
    }

    //Ans 1
    fun getmAnsOpt1(): Int {
        return mAnsOpt1
    }

    fun setmAnsOpt1(mAnsOpt1: Int) {
        this.mAnsOpt1 = mAnsOpt1
    }

    //Ans 2
    fun getmAnsOpt2(): Int {
        return mAnsOpt2
    }

    fun setmAnsOpt2(mAnsOpt2: Int) {
        this.mAnsOpt2 = mAnsOpt2
    }

    //Ans 3
    fun getmAnsOpt3(): Int {
        return mAnsOpt3
    }

    fun setmAnsOpt3(mAnsOpt3: Int) {
        this.mAnsOpt3 = mAnsOpt3
    }

    //Ans 4
    fun getmAnsOpt4(): Int {
        return mAnsOpt4
    }

    fun setmAnsOpt4(mAnsOpt4: Int) {
        this.mAnsOpt4 = mAnsOpt4
    }

    //Ans 5
    fun getmAnsOpt5(): Int {
        return mAnsOpt5
    }

    fun setmAnsOpt5(mAnsOpt5: Int) {
        this.mAnsOpt5 = mAnsOpt5
    }
}
