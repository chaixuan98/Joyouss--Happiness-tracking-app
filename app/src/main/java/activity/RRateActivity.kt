package activity

//screen after user has done at least one activity (evoked from entry)

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.R
import com.android.myapplication.todo.ResourcesActivity

class RRateActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aerate)

        //user needs more help
        var showbtn : TextView = findViewById(R.id.choose)
        var layout : LinearLayout = findViewById(R.id.helplayout)
        val btnone = findViewById<Button>(R.id.btnyesrepeat)


        btnone.setOnClickListener{
            layout.setVisibility(View.VISIBLE)
            showbtn.setVisibility(View.VISIBLE)
        }
        //navigate to set reminder screen
        val btntwo = findViewById<Button>(R.id.btnno)
        btntwo.setOnClickListener{
            startActivity(Intent(this@RRateActivity, SetReminderActivity::class.java))
            finish()
        }
        //user repeats another activity
        val backAct = findViewById<Button>(R.id.btnback)
        backAct.setOnClickListener {
            startActivity(Intent(this@RRateActivity,RMainActivity::class.java))
            finish()
        }

        //navigate to resource page
        val nextAct = findViewById<Button>(R.id.btnresource)
        nextAct.setOnClickListener {
            startActivity(Intent(this@RRateActivity,RResourcesActivity::class.java))
            finish()
        }
    }
}