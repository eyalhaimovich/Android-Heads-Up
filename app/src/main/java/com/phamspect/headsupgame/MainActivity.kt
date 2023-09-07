package com.phamspect.headsupgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    //vars
    private lateinit var startButton: Button
    private lateinit var category: String
    //TODO make dictionary key:value cat1:listof(words)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vars to id
        startButton = findViewById(R.id.startButt)

        //category buttons
        val buttonIds = listOf(
            R.id.cat1, R.id.cat2, R.id.cat3,
            R.id.cat4, R.id.cat5, R.id.cat6,
            R.id.cat7, R.id.cat8, R.id.cat9
        )
        for (buttonId in buttonIds) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                category = button.text.toString()
            }
        }

        //start button listener
        startButton.setOnClickListener {
            //intent to launch 2nd activity (ingame)
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            //add variables to transfer
            // TODO MAKE THIS TRANSFER OVER TO ACTIVITY2
            val list = listOf("df", "fr")
            intent.putStringArrayListExtra("myVariableKey", ArrayList(list))
            //start it
            startActivity(intent)
        }
    }
}