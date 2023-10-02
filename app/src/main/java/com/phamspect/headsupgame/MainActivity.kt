package com.phamspect.headsupgame

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.phamspect.headsupgame.databinding.ActivityMain2Binding
import com.phamspect.headsupgame.databinding.ActivityMainBinding
import com.phamspect.headsupgame.databinding.LoadingBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val catsLiveData = MutableLiveData<List<Int>>()

    //use binding for xml/viewModel vars
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivity2Binding: ActivityMain2Binding
    private lateinit var loadingBinding: LoadingBinding
    private val viewModel: viewModel by viewModels()
    //vars
    private var category: String = ""
    private var points: Int = 0
    private var right :Int = 0
    private var wrong :Int = 0
    //TODO make dictionary key:value cat1:listof(words)

    override fun onCreate(savedInstanceState: Bundle?) {
        //Use binding for layout/xml data link instead of manual
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainActivity2Binding = ActivityMain2Binding.inflate(layoutInflater)
        loadingBinding = LoadingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        makeMainMenu()
    }

    // make main menu setup
    private fun makeMainMenu() {
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding.score.text = viewModel.getPoints().toString()
        //category buttons
        makeCategoryButton()
        //start button listener
        makeStartButton()

        createObserver()
    }

    // add functionality to start button
    private fun makeStartButton(){
        //start button listener
        var startButton :Button = binding.startButt
        startButton.setBackgroundColor(Color.parseColor("#bdb1a8"))
        startButton.setOnClickListener {
            // change view to activity_main2 (main Game)
            if(catsLiveData.value?.isNotEmpty() == true){
                loadingScreen()
                viewModel.reset()
            }
            else{
                //
            }
        }
    }

    private fun loadingScreen() {
        setContentView(loadingBinding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        var textView : TextView = loadingBinding.countDown
        object :CountDownTimer(4000,1000){
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
                category = ""
            }
        }.start()
    }

    // handle main game loop ui
    private fun makeMainGame() {
        setContentView(mainActivity2Binding.root)
        //vars to id
        var currentTime = mainActivity2Binding.timer
        var word = mainActivity2Binding.randomWord
        var hit = hashSetOf<Int>()

        //using var to remove words from list when used in session
        var words = listOf("Apple", "Banana", "Cherry", "Date")

        //timer
        var timer:CountDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                viewModel.setInput(points, right, wrong)
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
        rightWrong(words, word, hit, timer)
    }

    private fun rightWrong(
        words: List<String>,
        word: TextView,
        hit: HashSet<Int>,
        timer: CountDownTimer
    ) {
        var right = mainActivity2Binding.rButton
        var wrong = mainActivity2Binding.wButton

        right.setOnClickListener {
            // if statement for testing
            if(hit.size == words.size){
                viewModel.right()
                timer.cancel()
                makeMainMenu()
            }
            else {
                viewModel.right()
                println(viewModel.getPoints())
                nextWord(words, word, hit)
            }

        }

        wrong.setOnClickListener {
            //if statement for testing
            if(hit.size == words.size){
                viewModel.wrong()
                timer.cancel()
                makeMainMenu()
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

    // function add functionality to buttons
    private fun makeCategoryButton(){
        val categoryButtons = listOf(
            binding.cat1, binding.cat2, binding.cat3,
            binding.cat4, binding.cat5, binding.cat6,
            binding.cat7, binding.cat8, binding.cat9
        )
        val defaultBackgroundResource = Color.parseColor("#d67f40")
        for (button in categoryButtons) {
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

    private fun createObserver(){
        catsLiveData.observe(this) { catsList ->
            var sButton = binding.startButt
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