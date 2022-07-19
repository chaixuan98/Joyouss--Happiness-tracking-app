package activity

//receive and handle broadcast intent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BcastRcver : BroadcastReceiver() {
    //upon receiving a broadcast intent
    override fun onReceive(context: Context, intent: Intent?) {
        //create and send notification
        NotificationHelper(context,"Click me to track your happiness level now!").Notification()
    }
}