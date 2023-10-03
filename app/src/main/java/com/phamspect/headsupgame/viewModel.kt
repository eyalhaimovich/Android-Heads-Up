package com.phamspect.headsupgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class viewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    //vars
    private var points: Int = 0
    private var right: Int = 0
    private var wrong: Int = 0
    private var catsLiveData = MutableLiveData<List<Int>>()

    fun setInput(points: Int, right: Int, wrong: Int) {
        this.points = points
        this.right = right
        this.wrong = wrong
    }

    fun getPoints(): Int{
        return points
    }

    fun getCatsLiveData(): MutableLiveData<List<Int>>{
        return catsLiveData
    }

    fun updateCatsList(newList: List<Int>) {
        catsLiveData.value = newList
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