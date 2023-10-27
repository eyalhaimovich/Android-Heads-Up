package com.phamspect.headsupgame

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.phamspect.headsupgame.databinding.EndingBinding

class EndingAct : AppCompatActivity() {
    private lateinit var binding: EndingBinding
    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = EndingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        intent.getIntArrayExtra("points")?.let { viewModel.setPointsData(it) }
        makeEnding()

    }

    private fun makeEnding() {
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.rightPts.text = viewModel.getRight().toString()
        binding.wrongPts.text = viewModel.getWrong().toString()
        binding.totalPts.text = viewModel.getPoints().toString()
        binding.retrun.setBackgroundColor(Color.YELLOW)
        binding.retrun.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}