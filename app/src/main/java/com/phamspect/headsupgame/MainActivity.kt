package com.phamspect.headsupgame

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //vars
    private val catsLiveData = MutableLiveData<List<Int>>()
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
        //start observer for category selection
        createObserver()
    }

    // add functionality to start button
    private fun makeStartButton(){
        //start button listener
        var startButton :Button = findViewById(R.id.startButt)
        startButton.setBackgroundColor(Color.parseColor("#bdb1a8"))

        startButton.setOnClickListener {
            // change view to activity_main2 (main Game)
            if(catsLiveData.value?.isNotEmpty() == true){
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
<<<<<<< Updated upstream
                var timer1 = makeTimer(startButton, makeMainGameUI(), null,30,1)
                timer1.start()
=======
                object :CountDownTimer(3000,1000){
                    override fun onTick(millisUntilFinished: Long) {
                        startButton.text = (millisUntilFinished/1000).toString()

                    }
                    override fun onFinish() {
                        makeMainGameUI()
                    }
                }.start()

            }
            else{
                //
>>>>>>> Stashed changes
            }
        }
    }

    private fun makeTimer(textView: TextView, f1: Unit?,f2: Unit?, start: Long, interval: Long, ) : CountDownTimer {
        var timer:CountDownTimer = object:CountDownTimer(start*1000, interval*1000){
            override fun onTick(p0: Long) {
                textView.text = (p0/1000).toString()
            }
            override fun onFinish() {
                f1
            }

        }
        return timer
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
        var timer:CountDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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

    //make buttons and their functionality
    private fun makeCategoryButton(){
        val buttonIds = listOf(
            R.id.cat1, R.id.cat2, R.id.cat3,
            R.id.cat4, R.id.cat5, R.id.cat6,
            R.id.cat7, R.id.cat8, R.id.cat9
        )
        val defaultBackgroundResource = Color.parseColor("#d67f40")
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            button.setBackgroundColor(defaultBackgroundResource)
            val buttonInt = button.text.toString().toInt()
            button.setOnClickListener {
                //change color of button and add/remove btn's int to category list
                val currentCats = catsLiveData.value ?: emptyList()
                if (buttonInt !in currentCats){
                    button.setBackgroundColor(Color.parseColor("#29c40e"))
                    val updatedCats = currentCats + buttonInt
                    catsLiveData.value = updatedCats
                }
                else{
                    button.setBackgroundColor(defaultBackgroundResource)
                    val updatedCats = currentCats - buttonInt
                    catsLiveData.value = updatedCats
                }
            }
        }
    }

    fun createObserver(){
        catsLiveData.observe(this) { catsList ->
            var sButton = findViewById<Button>(R.id.startButt)
            if (catsList.isEmpty()) {
                //grey out
                sButton.setBackgroundColor(Color.parseColor("#bdb1a8"))
            } else {
                //make normal color
                sButton.setBackgroundColor(Color.parseColor("#d67f40"))

            }
        }
    }

}