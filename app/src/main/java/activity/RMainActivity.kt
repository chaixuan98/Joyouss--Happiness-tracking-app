package activity

//screen showing activity evoked from entry when user has low happiness level

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.atype.*

class RMainActivity : AppCompatActivity(){

    //sharedpreference for initialising list of activities
    private val sharedPrefFile = "kotlinsharedpreference"

    companion object {
        lateinit var databaseHandler: DatabaseHandler //sqlite database handler
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.atype)

        databaseHandler = DatabaseHandler(this, null, null, 1)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        val sharedIdValue = sharedPreferences.getInt("init",0)
        if(sharedIdValue.equals(0)){
            initAct()
            editor.putInt("init",1)
            editor.apply()
            editor.commit()
        }
        else{}

        val showtxt = findViewById<TextView>(R.id.chooseact)
        val nexttxt = findViewById<TextView>(R.id.next)
        val next = findViewById<Button>(R.id.btnnext)

        //show energetic activity
        val btnone = findViewById<Button>(R.id.btnatypeone)
        btnone.setOnClickListener{
            viewActivities(1)
            showtxt.setVisibility(View.VISIBLE)
            nexttxt.setVisibility(View.VISIBLE)
            next.setVisibility(View.VISIBLE)
        }

        //show restless activity
        val btntwo = findViewById<Button>(R.id.btnatypetwo)
        btntwo.setOnClickListener{
            viewActivities(2)
            showtxt.setVisibility(View.VISIBLE)
            nexttxt.setVisibility(View.VISIBLE)
            next.setVisibility(View.VISIBLE)
        }

        //continue to next page
        next.setOnClickListener{
            startActivity(Intent(this@RMainActivity,RRateActivity::class.java))
            finish()
        }
    }

    //initialising all activities, store into db for first time
    private fun initAct(){
        val a2 = Activity(0,"do a 10-min workout", 1)
        databaseHandler.addActivity(this, a2)
        val a3 = Activity(0,"do yoga for 10 min", 1)
        databaseHandler.addActivity(this, a3)
        val a4 = Activity(0,"go cycling", 1)
        databaseHandler.addActivity(this, a4)
        val a5 = Activity(0,"go for a jog", 1)
        databaseHandler.addActivity(this, a5)
        val a6 = Activity(0,"do some gardening", 1)
        databaseHandler.addActivity(this, a6)
        val a7 = Activity(0,"tidy up your table", 1)
        databaseHandler.addActivity(this, a7)
        val a8 = Activity(0,"tidy up your room", 1)
        databaseHandler.addActivity(this, a8)
        val a9 = Activity(0,"dance like nobody is watching", 1)
        databaseHandler.addActivity(this, a9)
        val a16 = Activity(0,"sing like nobody is watching", 1)
        databaseHandler.addActivity(this, a16)
        val a10 = Activity(0,"bake/cook something", 1)
        databaseHandler.addActivity(this, a10)
        val a11 = Activity(0,"complete a puzzle/sudoku", 1)
        databaseHandler.addActivity(this, a11)
        val a1 = Activity(0,"paint something", 2)
        databaseHandler.addActivity(this, a1)
        val a12 = Activity(0,"treat yourself some snacks", 2)
        databaseHandler.addActivity(this, a12)
        val a13 = Activity(0,"treat yourself some dark chocolate", 2)
        databaseHandler.addActivity(this, a13)
        val a14 = Activity(0,"watch your favourite movie/sitcom", 2)
        databaseHandler.addActivity(this, a14)
        val a15 = Activity(0,"read your favourite book", 2)
        databaseHandler.addActivity(this, a15)
        val a17 = Activity(0,"write something on a paper", 2)
        databaseHandler.addActivity(this, a17)
        val a18 = Activity(0,"squeeze a rubbery stress ball", 2)
        databaseHandler.addActivity(this, a18)
        val a19 = Activity(0,"look back at your memorable photos", 2)
        databaseHandler.addActivity(this, a19)
        val a20 = Activity(0,"wrap yourself in a warm blanket", 2)
        databaseHandler.addActivity(this, a20)
        val a21 = Activity(0,"gaze out of your window", 2)
        databaseHandler.addActivity(this, a21)
        val a22 = Activity(0,"listen to your favourite music", 2)
        databaseHandler.addActivity(this, a22)
        val a23 = Activity(0,"give yourself a hand/neck massage", 2)
        databaseHandler.addActivity(this, a23)
        val a24 = Activity(0,"medidate for 10 min", 2)
        databaseHandler.addActivity(this, a24)
        val a25 = Activity(0,"take a bath/shower", 2)
        databaseHandler.addActivity(this, a25)
        val a26 = Activity(0,"talk to someone", 2)
        databaseHandler.addActivity(this, a26)
        val a27 = Activity(0,"take a nap", 2)
        databaseHandler.addActivity(this, a27)
    }

    //bind activities in recyclerview
    private fun viewActivities(type: Int){
        val activitieslist = databaseHandler.getActivities(this, type)
        val adapter = ActAdapter(this, activitieslist)
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        rv.adapter=adapter
    }
}