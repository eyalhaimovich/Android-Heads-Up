package com.phamspect.headsupgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    //vars
    private lateinit var startButton: Button
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vars to id
        startButton = findViewById(R.id.startButt)


        //start button listener
        startButton.setOnClickListener {
            //intent to launch 2nd activity (ingame)
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            //values of (chosenKey) -> List[words]
            //start it
            startActivity(intent)
        }
    }

    private fun makeCategoryButtons(){
        setContentView(R.layout.activity_main)

        var button:Button = findViewById(R.id.cat1)

        button.setOnClickListener{
            category = button.text.toString()
        }
        var button2 = findViewById<Button>(R.id.cat2)
        button2.setOnClickListener{
            category = button2.text.toString();
        }
        var button3 = findViewById<Button>(R.id.cat3)
        button3.setOnClickListener{
            category = button3.text.toString();
        }
        var button4 = findViewById<Button>(R.id.cat4)
        button4.setOnClickListener{
            category = button4.text.toString();
        }
        var button5 = findViewById<Button>(R.id.cat5)
        button5.setOnClickListener{
            category = button5.text.toString();
        }
        var button6 = findViewById<Button>(R.id.cat6)
        button6.setOnClickListener{
            category = button6.text.toString();
        }
        var button7 = findViewById<Button>(R.id.cat7)
        button7.setOnClickListener{
            category = button7.text.toString();
        }
        var button8 = findViewById<Button>(R.id.cat8)
        button8.setOnClickListener{
            category = button8.text.toString();
        }
        var button9 = findViewById<Button>(R.id.cat9)
        button9.setOnClickListener{
            category = button9.text.toString();
        }
    }

}