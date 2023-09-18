package com.phamspect.headsupgame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {

    //vars
    private lateinit var currentTime: TextView
    private lateinit var timer: CountDownTimer
    private lateinit var word: TextView
    private lateinit var nextWord: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //vars to id
        currentTime = findViewById(R.id.timer)
        word = findViewById(R.id.randomWord)
        nextWord = findViewById(R.id.nextWord)
        //using var to remove words from list when used in session
        var words = listOf("Apple", "Banana", "Cherry", "Date")

        //timer
        timer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                //TODO Change to Results screen
                currentTime.text = "done!"
            }
        }.start()

        //next word
        nextWord.setOnClickListener {
            val randomIndex = Random.nextInt(words.size)
            val randomWord = words[randomIndex]
            word.text = randomWord
            //TODO change to new random word
        }
    }
}