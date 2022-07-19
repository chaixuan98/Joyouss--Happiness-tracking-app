package activity

//screen suggesting user to do an activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.myapplication.todo.R
import kotlinx.android.synthetic.main.asuggest.*

class SuggestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asuggest)

        val btnYes = findViewById<Button>(R.id.buttonYes)
        val btnNo = findViewById<Button>(R.id.buttonNo)
        btnYes.setOnClickListener{
            startActivity(Intent(this@SuggestActivity, RMainActivity::class.java))
            finish()
        }
        btnNo.setOnClickListener{
            startActivity(Intent(this@SuggestActivity, SetReminderActivity::class.java))
            finish()
        }
    }
}
