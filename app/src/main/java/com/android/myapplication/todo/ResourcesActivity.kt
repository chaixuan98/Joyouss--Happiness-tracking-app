package com.android.myapplication.todo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resources.*

class ResourcesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        pcopy1.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("0145351100", "0145351100")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied successfully", Toast.LENGTH_SHORT).show()
        }

        pcopy2.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("042815161", "042815161")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied successfully", Toast.LENGTH_SHORT).show()
        }

        pcopy3.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("042280342", "042280342")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied successfully", Toast.LENGTH_SHORT).show()
        }

        pcopy4.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("043988340", "043988340")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied successfully", Toast.LENGTH_SHORT).show()
        }

        pcopy5.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("0376272929", "0376272929")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied successfully", Toast.LENGTH_SHORT).show()
        }


    }
}