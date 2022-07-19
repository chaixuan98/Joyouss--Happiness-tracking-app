package activity

//screen after user has done at least one activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.HomeActivity
import com.android.myapplication.todo.R
import com.android.myapplication.todo.ResourcesActivity

class RateActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arate)

        var showbtn : TextView = findViewById(R.id.choose)
        var layout : LinearLayout = findViewById(R.id.helplayout)
        val btnone = findViewById<Button>(R.id.btnyesrepeat)

        //user needs more help (yes)
        btnone.setOnClickListener{
            layout.setVisibility(View.VISIBLE)
            showbtn.setVisibility(View.VISIBLE)
        }
        //navigate to homepage (no)
        val btntwo = findViewById<Button>(R.id.btnno)
        btntwo.setOnClickListener{
            startActivity(Intent(this@RateActivity, HomeActivity::class.java))
            finish()
        }

        //user repeats another activity
        val backAct = findViewById<Button>(R.id.btnback)
        backAct.setOnClickListener {
            startActivity(Intent(this@RateActivity,MainActivity::class.java))
            finish()
        }
        //navigate to resource page
        val nextAct = findViewById<Button>(R.id.btnresource)
        nextAct.setOnClickListener {
            startActivity(Intent(this@RateActivity,ResourcesActivity::class.java))
            finish()
        }
    }
}