package com.example.imarticus_class_3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class SmsReceiver() : BroadcastReceiver(){
    override fun onReceive(context: Context? , intent: Intent?) {
        val message= Telephony.Sms.Intents.getMessagesFromIntent(intent)
        val body=message[0].messageBody
        val sender=message[0].originatingAddress
        Log.i("message","you got message $body $sender")
    }
}