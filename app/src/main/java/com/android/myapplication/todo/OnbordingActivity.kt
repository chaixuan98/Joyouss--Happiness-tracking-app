package com.android.myapplication.todo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.android.myapplication.todo.adapters.OnBoardingViewPagerAdapter
import com.android.myapplication.todo.data.OnBoardingData
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_onbording.*

class OnbordingActivity : AppCompatActivity() {
    var OnBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout?= null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Track your happiness","Record your mood and thoughts everyday", R.drawable.onboarding_1))
        onBoardingData.add(OnBoardingData("Happiness Statistic", "Provide analysis based all your mood entries", R.drawable.onboarding_2))
        onBoardingData.add(OnBoardingData("Happiness Booster","Suggest activities based on energy level to boost your happiness level", R.drawable.onboarding_3))
        onBoardingData.add(OnBoardingData("Set Reminder","Build-in personalised reminder so that you won't forget to track your happiness everyday", R.drawable.onboarding_4))
        onBoardingData.add(OnBoardingData("Happiness Baseline Test","A simple and fun test to determine your happiness level baseline", R.drawable.onboarding_5))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener {

            if(position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }

            if (position == onBoardingData.size  ){
                val i = Intent(applicationContext, QuizActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        tabLayout!!.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                position = tab!!.position
                if(tab.position == onBoardingData.size - 1){
                    next!!.text = "Start Test"
                }
                else{
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.screenPager)
        OnBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = (OnBoardingViewPagerAdapter)
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }
}