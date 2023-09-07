package com.phamspect.headsupgame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    //vars
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var currentTime: TextView
    private lateinit var timer: CountDownTimer
    private  var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vars to id
        //startButton = findViewById(R.id.start_button)
        //stopButton = findViewById(R.id.stop_button)
        //currentTime = findViewById(R.id.curr_time)

        //start button listener

        startButton.setOnClickListener {
            if(!isRunning){
                timer = object : CountDownTimer(30000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        currentTime.text = (millisUntilFinished / 1000).toString()
                    }

                    override fun onFinish() {
                        currentTime.text = "done!"
                    }

                }.start()
                isRunning = true
            }else{
                //Say timer is already running
            }
        }
        //end button listener
        stopButton.setOnClickListener {
            if(isRunning){
                timer.cancel()
                isRunning = false
            }
        }
    }
}