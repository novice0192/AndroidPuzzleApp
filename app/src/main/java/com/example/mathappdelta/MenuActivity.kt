package com.example.mathappdelta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val score = intent.getIntExtra("score", 0)
        val score_disp: TextView = findViewById(R.id.screPView)
        score_disp.text = "Score:\n" + score.toString()
        val newgame: Button = findViewById(R.id.newgame)
        newgame.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}