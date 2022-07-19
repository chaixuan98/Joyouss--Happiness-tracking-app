package activity

//for sending and creating notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.myapplication.todo.SplashActivity
import com.android.myapplication.todo.R

class NotificationHelper(var co:Context, var msg:String) {

    private val CHANNEL_ID = "joyous"
    private val NOTIFICATION_ID=123

    //create a new notification channel and set notification
    fun Notification(){
        createNotificationChannel()
        //navigate user to splash activity page when user clicks on the notification message
        val senInt = Intent(co, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingInt = PendingIntent.getActivities(co,0, arrayOf(senInt),0)

        //notification dialog
        val icon = BitmapFactory.decodeResource(co.resources, R.drawable.joy) //icon
        val isnotification = NotificationCompat.Builder(co,CHANNEL_ID)
            .setSmallIcon(R.drawable.joyous_gif)
            .setLargeIcon(icon)
            .setContentTitle("Joyous")
            .setContentText(msg)
            .setContentIntent(pendingInt)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build() //build notification
        NotificationManagerCompat.from(co)
            .notify(NOTIFICATION_ID,isnotification) //send notification
    }

    //create a notification channel
    private fun createNotificationChannel(){
        //only Android 8 API level 26 onwards support notification channels
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            val descrip = "This is a notification channel for Joyous app"
            val imports = NotificationManager.IMPORTANCE_HIGH
            val cannels = NotificationChannel(CHANNEL_ID,name,imports).apply {
                description = descrip
            }
            val notificationManger = co.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManger.createNotificationChannel(cannels)
        }
    }

}