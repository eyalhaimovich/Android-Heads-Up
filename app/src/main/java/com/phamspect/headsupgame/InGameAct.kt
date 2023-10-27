package com.phamspect.headsupgame

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.phamspect.headsupgame.databinding.IngameactBinding
import com.phamspect.headsupgame.databinding.LoadingBinding
import kotlin.random.Random

class InGameAct : AppCompatActivity(){
    private lateinit var binding: IngameactBinding
    private lateinit var loadingBinding: LoadingBinding
    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = IngameactBinding.inflate(layoutInflater)
        loadingBinding = LoadingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var seleted = intent.getStringExtra("liveData")?: ""
        viewModel.setCategoryLiveData(seleted)
        loadingScreen()

    }
    private fun loadingScreen() {
        setContentView(loadingBinding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        var textView : TextView = loadingBinding.countDown
        object : CountDownTimer(4000,1000){
            override fun onTick(p0: Long) {
                var layout = loadingBinding.layOut
                textView.text = (p0/1000).toString()
                if(p0<1000){
                    layout.setBackgroundColor(Color.parseColor("#4CAF50"))
                    textView.text = "GO!"
                }
                else {
                    layout.setBackgroundColor(Color.RED)
                }
            }
            override fun onFinish() {
                makeMainGame()
            }
        }.start()
    }

    // handle main game loop ui
    private fun makeMainGame() {
        setContentView(binding.root)
        //vars to id
        var currentTime = binding.timer
        var word = binding.randomWord
        var hit = hashSetOf<Int>()
        //reset viewmodel's points
        viewModel.reset()

        //get category of words
        val category = viewModel.getCategoryLiveData().value.toString()
        val words = viewModel.getCategoryWords(category)
        //timer
        var timer: CountDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                makeEnding()
            }

        }.start()

        //next word
        nextWord(words,word, hit)
        // right and wrong button handler
        rightWrong(words, word, hit, timer)
    }
    private fun rightWrong(words: List<String>, word: TextView, hit: HashSet<Int>, timer: CountDownTimer) {
        var right = binding.rButton
        var wrong = binding.wButton

        right.setOnClickListener {
            // if statement for testing
            if(hit.size == words.size){
                viewModel.right()
                timer.cancel()
                makeEnding()
            }
            else {
                viewModel.right()
                nextWord(words, word, hit)
            }

        }

        wrong.setOnClickListener {
            //if statement for testing
            if(hit.size == words.size){
                viewModel.wrong()
                timer.cancel()
                makeEnding()
            }
            else {
                viewModel.wrong()
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

    private fun makeEnding(){
        val intent = Intent(this, EndingAct::class.java)
        intent.putExtra("points",viewModel.getPointsData())
        startActivity(intent)
    }
}