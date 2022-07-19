package com.android.myapplication.todo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 1500
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        val sharedIdValue = sharedPreferences.getInt("onboard",0)

        Handler().postDelayed({
            if(sharedIdValue.equals(0)){
                editor.putInt("onboard",1)
                editor.apply()
                editor.commit()
                val intent = Intent(this@SplashActivity, OnbordingActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this@SplashActivity, crudmoodstatus.MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, SPLASH_TIMEOUT)
    }
}