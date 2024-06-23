package com.example.imarticus_class_3

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MianViewModel: ViewModel() {
    //val _seconds = MutableLiveData<Int>()
    var _second =MutableLiveData<Int>() //0
    val tag=MianViewModel::class.java.simpleName
    lateinit var timer: CountDownTimer
    var count=0
    fun increment(){
        count++
    }
    fun startTimer(){
        timer=object : CountDownTimer(10_000,1_000){

            override fun onTick(millisUntilFinished: Long) {
                _second.value= (millisUntilFinished/1000).toInt()
                Log.d(tag,"${(millisUntilFinished/1000).toInt()}")
            }

            override fun onFinish() {
                Log.d(tag,"finished")
            }

        }.start()
    }

}