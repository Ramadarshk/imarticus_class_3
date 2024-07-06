package com.example.imarticus_class_3

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }
        val button2: Button =findViewById(R.id.button2)
        button2.setOnClickListener {

            /*val smsManager1:SmsManager =if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                this.getSystemService(SmsManager::class.java) as SmsManager}
            else{
                SmsManager.getDefault()
            }
            smsManager1.sendTextMessage("+919848647507",null,"happy Adarsh day",null,null)
            Log.i("helo","hi")*/
            Log.i("helo", SystemClock.elapsedRealtime().toString())
            val alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
            val triggertime=System.currentTimeMillis()+10*60
            val intent=Intent(this,AlarmSet::class.java).apply {
                flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingintent:PendingIntent=PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_IMMUTABLE)
           alarmManager.set(AlarmManager.RTC,triggertime,pendingintent,)
        // alarmManager.setExact(AlarmManager.RTC_WAKEUP,triggertime,pendingintent)
        }

    }
    fun notify(view: View){
        onCreateNotification()
        val intent=Intent(this,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingintent:PendingIntent=PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_IMMUTABLE)
        var bulder=NotificationCompat.Builder(this,"channel name")
            .setSmallIcon(R.drawable.baseline_store_24)
            .setContentTitle("hello")
            .setContentText("dev app")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingintent)
        val notificationMangerR:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationMangerR.notify(1,bulder.build())
    }

    fun onCreateNotification (){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name="promotions channel"
            val descriptiont="channel description"
            val important=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel("channel name",name,important).apply {
                description=descriptiont
            }
            val NotificationManageR:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            NotificationManageR.createNotificationChannel(channel)
        }
    }

}