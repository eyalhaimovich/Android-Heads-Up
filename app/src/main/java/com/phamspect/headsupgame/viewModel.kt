package com.phamspect.headsupgame

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class viewModel(
                private val savedStateHandle: SavedStateHandle): ViewModel() {
    //vars
    private var points: Int = 0
    private var right: Int = 0
    private var wrong: Int = 0
    private var catsLiveData = MutableLiveData<List<String>>()
    private var categoryMap = mutableMapOf<String, List<String>>()

    fun getPoints(): Int{
        return points
    }

    fun getCatsLiveData(): MutableLiveData<List<String>>{
        return catsLiveData
    }

    fun updateCatsList(newList: List<String>) {
        catsLiveData.value = newList
    }

    fun addKeystoMap(key: String){
        categoryMap[key] = emptyList<String>()
    }
    fun printMapKeys(): MutableSet<String> {
        return categoryMap.keys
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