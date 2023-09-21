package com.phamspect.headsupgame

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //vars
    private var category: String = ""
    //TODO make dictionary key:value cat1:listof(words)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeMainMenu()
    }

    // make main menu setup
    private fun makeMainMenu() {
        setContentView(R.layout.activity_main)
        //category buttons
        makeCategoryButton()
        //start button listener
        makeStartButton()
    }

    // add functionality to start button
    private fun makeStartButton(){
        //start button listener
        var startButton :Button = findViewById(R.id.startButt)


        startButton.setOnClickListener {
            // change view to activity_main2 (main Game)
            if(category.isNotBlank()){

                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                var timer :CountDownTimer = object :CountDownTimer(3000,1000){
                    override fun onTick(p0: Long) {
                        startButton.text = (p0/1000).toString()

                    }
                    override fun onFinish() {
                        makeMainGameUI()
                    }
                }.start()

            }
        }
    }

    // handel main game loop ui
    private fun makeMainGameUI() {
        setContentView(R.layout.activity_main2)
        //vars to id
        var currentTime: TextView = findViewById(R.id.timer)
        var word: TextView = findViewById(R.id.randomWord)
        var nextWord: Button = findViewById(R.id.nextWord)
        //using var to remove words from list when used in session
        var words = listOf("Apple", "Banana", "Cherry", "Date")

        //timer
        var timer:CountDownTimer = object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                category = ""
                makeMainMenu()
            }

        }.start()
        //next word
        nextWord.setOnClickListener {
            val randomIndex = Random.nextInt(words.size)
            val randomWord = words[randomIndex]
            word.text = randomWord
        }
    }

    // function add functionality to buttons
    private fun makeCategoryButton(){
        val buttonIds = listOf(
            R.id.cat1, R.id.cat2, R.id.cat3,
            R.id.cat4, R.id.cat5, R.id.cat6,
            R.id.cat7, R.id.cat8, R.id.cat9
        )
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                category = button.text.toString()
            }
        }
    }

}