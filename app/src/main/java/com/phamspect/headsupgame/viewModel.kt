package com.phamspect.headsupgame

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class viewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    //vars
    private var points: Int = 0
    private var right: Int = 0
    private var wrong: Int = 0

    fun setInput(points: Int, right: Int, wrong: Int) {
        this.points = points
        this.right = right
        this.wrong = wrong
    }

    fun getPoints(): Int{
        return points
    }

    fun right(){
        right++
        points++
    }

    fun wrong(){
        wrong++
        points--
    }

    fun reset(){
        right = 0
        wrong = 0
        points = 0
    }
}