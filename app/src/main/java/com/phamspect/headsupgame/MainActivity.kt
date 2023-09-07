package com.phamspect.headsupgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    //vars
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vars to id
        startButton = findViewById(R.id.startButt)


        //start button listener
        startButton.setOnClickListener {
            //intent to launch 2nd activity (ingame)
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            //values of (chosenKey) -> List[words]
            //start it
            startActivity(intent)
        }
    }
}