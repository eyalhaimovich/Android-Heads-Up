package com.phamspect.headsupgame

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //vars
    private var points: Int = 0
    private var right :Int = 0
    private var wrong :Int = 0
    private val catsLiveData = MutableLiveData<List<Int>>()

    private var selected:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeMainMenu()
    }

    // make main menu setup
    private fun makeMainMenu() {
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //category buttons
        //makeCategoryButton()
        makeCatButton2()
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
        startButton.isEnabled = false
        startButton.setOnClickListener {
            // change view to activity_main2 (main Game)
//            if(catsLiveData.value?.isNotEmpty() == true){
//                loadingScreen()
//            }
            if(selected != null){
                loadingScreen()
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
            }
        }.start()
    }

    // handel main game loop ui
    @SuppressLint("MissingInflatedId")
    private fun makeMainGame() {
        setContentView(R.layout.activity_main2)
        //vars to id
        var currentTime: TextView = findViewById(R.id.timer)
        var word: TextView = findViewById(R.id.randomWord)

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

    private fun makeCatButton2(){
        val start: Button = findViewById(R.id.startButt)
        val grid : LinearLayout = findViewById(R.id.gridL)
        val cCount = grid.childCount
        for(i in 0 ..<cCount){
            val button : Button = grid.getChildAt(i) as Button
            button.setBackgroundColor(Color.GREEN)
            button.setOnClickListener {
                if(selected != null){
                    selected!!.setBackgroundColor(Color.GREEN)
                }
                if(button == selected){
                    selected = null
                    start.setBackgroundColor(Color.LTGRAY)
                    start.isEnabled = false
                }
                else{
                    selected = button
                    button.setBackgroundColor(Color.LTGRAY)
                    start.setBackgroundColor(Color.GREEN)
                    start.isEnabled = true
                }
            }
        }
    }

}