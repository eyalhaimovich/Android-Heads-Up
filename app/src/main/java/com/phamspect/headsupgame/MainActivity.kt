package com.phamspect.headsupgame

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //vars
    private var category: String = ""
    private var points: Int = 0
    private var right :Int = 0
    private var wrong :Int = 0
    //TODO make dictionary key:value cat1:listof(words)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeMainMenu()
    }

    // make main menu setup
    private fun makeMainMenu() {
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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
                loadingScreen()
            }
            else{
                //
            }
        }
    }

    private fun loadingScreen() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.loading)
        var textView : TextView = findViewById(R.id.countDown)
        object :CountDownTimer(4000,1000){
            override fun onTick(p0: Long) {
                textView.text = (p0/1000).toString()
                if(p0<1000){
                    var layout = findViewById<View>(R.id.layOut)
                    layout.setBackgroundColor(Color.parseColor("#4CAF50"))
                    textView.text = "GO!"
                }
            }
            override fun onFinish() {
                makeMainGame()
                category = ""
            }
        }.start()
    }

    // handel main game loop ui
    private fun makeMainGame() {
        setContentView(R.layout.activity_main2)
        //vars to id
        var currentTime: TextView = findViewById(R.id.timer)
        var word: TextView = findViewById(R.id.randomWord)
        var nextWord: Button = findViewById(R.id.nextWord)
        var hit :HashSet<Int> = hashSetOf<Int>()

        //using var to remove words from list when used in session
        var words = listOf("Apple", "Banana", "Cherry", "Date")

        //timer
        var timer:CountDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                points = 0
                makeMainMenu()
            }

        }.start()
        //next word
        nextWord(words,word, hit)
//        nextWord.setOnClickListener {
//            val randomIndex = Random.nextInt(words.size)
//            val randomWord = words[randomIndex]
//            word.text = randomWord
//        }

        // right and wrong button handler
        rightWrong(words, word, hit)
    }

    private fun rightWrong(words: List<String>, word: TextView, hit: HashSet<Int>) {
        var right : Button = findViewById(R.id.rButton)
        var wrong : Button = findViewById(R.id.wButton)

        right.setOnClickListener {
            // if statement for testing
            if(hit.size == words.size){
                makeMainMenu()
            }
            else {
                this.right++
                this.points++
                nextWord(words, word, hit)
            }

        }

        wrong.setOnClickListener {
            //if statement for testing
            if(hit.size == words.size){
                makeMainMenu()
            }
            else {
                this.wrong++
                nextWord(words, word, hit)
            }
        }
    }

    private fun nextWord(words: List<String>, word: TextView, hit: HashSet<Int>) {
        var ran = Random.nextInt(words.size)
        if(hit.isNotEmpty()){
            while(hit.contains(ran)){
                ran = Random.nextInt(words.size)
            }
            hit.add(ran)
            word.text = words[ran]
        }
        hit.add(ran)
        word.text = words[ran]
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