package com.phamspect.headsupgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class viewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    //vars
    private var points: Int = 0
    private var right: Int = 0
    private var wrong: Int = 0
    private var categoryLiveData = MutableLiveData<String>()
    private var categoryMap = mapOf(
        "Medicine" to listOf(
            "Insulin", "Advil", "Tylenol", "Aspirin", "Antibiotic",
            "Morphine", "Xanax", "Penicillin", "Ibuprofen", "Vicodin"
        ),
        "Math" to listOf(
            "Calculus", "Algebra", "Geometry", "Trigonometry", "Statistics",
            "Arithmetic", "Linear Algebra", "Calculations", "Equations", "Probability"
        ),
        "Science" to listOf(
            "Chemistry", "Biology", "Physics", "Astronomy", "Geology",
            "Microbiology", "Botany", "Zoology", "Meteorology", "Genetics"
        ),
        "Technology" to listOf(
            "Computer", "Internet", "Smartphone", "Software", "Hardware",
            "Programming", "Artificial Intelligence", "Virtual Reality", "Blockchain", "Cybersecurity"
        ),
        "Food" to listOf(
            "Pizza", "Sushi", "Chocolate", "Pasta", "Ice Cream",
            "Hamburger", "Salad", "Bacon", "Coffee", "Tacos"
        ),
        "Movies" to listOf(
            "Titanic", "Inception", "Avatar", "Jurassic Park", "Star Wars",
            "The Matrix", "Gladiator", "Lord of the Rings", "The Shawshank Redemption", "The Godfather"
        ),
        "Animals" to listOf(
            "Lion", "Elephant", "Tiger", "Giraffe", "Penguin",
            "Dolphin", "Kangaroo", "Koala", "Polar Bear", "Gorilla"
        ),
        "Places" to listOf(
            "Paris", "New York", "London", "Tokyo", "Rome",
            "Sydney", "Barcelona", "Dubai", "Rio de Janeiro", "Venice"
        ),
        "Songs" to listOf(
            "I Gotta Feeling", "Uptown Funk", "Thriller", "Bohemian Rhapsody", "Imagine",
            "Billie Jean", "Hotel California", "Rolling in the Deep", "Like a Rolling Stone", "Hey Jude"
        ))

    fun getPoints(): Int{
        return points
    }

    fun getCategoryLiveData(): MutableLiveData<String>{
        return categoryLiveData
    }

    fun setCategoryLiveData(newCat: String) {
        categoryLiveData.value = newCat
    }

    fun getCategoryWords(category: String): List<String> {
        return categoryMap[category]?: emptyList()
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
/*
fun addKeystoMap(key: String){
    categoryMap[key] = emptyList<String>()
}
fun printMapKeys(): MutableSet<String> {
    return categoryMap.keys
}
 */