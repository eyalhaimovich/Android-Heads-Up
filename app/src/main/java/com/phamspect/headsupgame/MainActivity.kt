package com.phamspect.headsupgame

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.phamspect.headsupgame.databinding.ActivityMain2Binding
import com.phamspect.headsupgame.databinding.ActivityMainBinding
import com.phamspect.headsupgame.databinding.LoadingBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val TAG = "mainActivity"
    //use binding for xml/viewModel varsa
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivity2Binding: ActivityMain2Binding
    private lateinit var loadingBinding: LoadingBinding
    private val viewModel: viewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        //Use binding for layout/xml data link instead of manual
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainActivity2Binding = ActivityMain2Binding.inflate(layoutInflater)
        loadingBinding = LoadingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        makeTitleScreen()
    }

    private fun makeTitleScreen() {
        setContentView(binding.root)
        visibility(View.INVISIBLE)
        binding.scoreTitle.setTextColor(Color.WHITE)
        binding.appName.textSize = 83f
        binding.scoreTitle.visibility = View.VISIBLE
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setVerticalBias(.40f, R.id.layout, R.id.appName)

        binding.root.setOnClickListener{
            makeMainMenu()
        }
    }

    // function use to change vertical position of element
    private fun setVerticalBias(float: Float, constraintLayout: Int, view : Int){
        val constraint : ConstraintLayout = findViewById(constraintLayout)
        val set = ConstraintSet()
        set.clone(constraint)
        set.setVerticalBias(view, float)
        set.applyTo(constraint)
    }

    private fun visibility(visibility : Int){
        binding.catTxt.visibility = visibility
        binding.startButt.visibility = visibility
        val categoryButtons = listOf(
            binding.cat1, binding.cat2, binding.cat3,
            binding.cat4, binding.cat5, binding.cat6,
            binding.cat7, binding.cat8, binding.cat9
        )

        for(butt in categoryButtons){
            butt.visibility = visibility
        }
    }

    // make main menu setup
    private fun makeMainMenu() {
        setContentView(binding.root)
        visibility(View.VISIBLE)
        binding.scoreTitle.visibility = View.INVISIBLE
        binding.appName.textSize = 63f

        setVerticalBias(0f, R.id.layout, R.id.appName)

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
        startButton.isActivated = false
        startButton.isEnabled = false
        startButton.setOnClickListener {
            // change view to activity_main2 (main Game)
            loadingScreen()
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
                viewModel.setCategoryLiveData("")
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
        //reset viewmodel's points
        viewModel.reset()

        //get category of words
        val category = viewModel.getCategoryLiveData().value.toString()
        val words = viewModel.getCategoryWords(category)
        //timer
        var timer:CountDownTimer = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                currentTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
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

    private fun rightWrong(words: List<String>, word: TextView, hit: HashSet<Int>, timer: CountDownTimer) {
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
        val defaultCatButtonColor = Color.parseColor("#d67f40")
        val selectedCatButtonColor = Color.parseColor("#29c40e")
        var selectedCategoryButton: Button? = null

        for (button in categoryButtons) {
            button.setBackgroundColor(defaultCatButtonColor)
            //viewModel.addKeystoMap(buttonText)
            button.setOnClickListener {
                if (selectedCategoryButton != null) {
                    // Deselect the previously selected button
                    selectedCategoryButton?.setBackgroundColor(defaultCatButtonColor)
                }
                if (button != selectedCategoryButton) {
                    // Select the new category button
                    button.setBackgroundColor(selectedCatButtonColor)
                    selectedCategoryButton = button
                    viewModel.setCategoryLiveData(button.text.toString())
                } else {
                    // Deselect the currently selected button
                    selectedCategoryButton = null
                    viewModel.setCategoryLiveData("")
                }
            }
        }
    }

    private fun createObserver(){
        viewModel.getCategoryLiveData().observe(this) { catsList ->
            var sButton = binding.startButt
            if (catsList.isEmpty()) {
                //grey out
                sButton.setBackgroundColor(Color.parseColor("#bdb1a8"))
                sButton.isActivated = false
                sButton.isEnabled = false
            } else {
                //make normal color
                sButton.setBackgroundColor(Color.parseColor("#d67f40"))
                sButton.isActivated = true
                sButton.isEnabled = true

            }
        }
    }
}