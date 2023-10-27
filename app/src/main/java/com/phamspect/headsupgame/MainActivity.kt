package com.phamspect.headsupgame

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.phamspect.headsupgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var selected : Button? = null
    //use binding for xml/viewModel varsa
    private lateinit var binding: ActivityMainBinding
    private val viewModel: viewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        //Use binding for layout/xml data link instead of manual
        binding = ActivityMainBinding.inflate(layoutInflater)

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
            binding.root.isEnabled = false
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
        //start observer for category selection
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
            val intent = Intent(this, InGameAct::class.java)
            intent.putExtra("liveData",viewModel.getCategoryLiveData().value)
            startActivity(intent)
        }
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