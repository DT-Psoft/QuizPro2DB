package com.example.quizapppro2.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.quizapppro2.R

class ScoreboardActivity : AppCompatActivity() {
    lateinit var list: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
         list = findViewById(R.id.score_listview)
        val valores = Score.score.scoreboardName.toTypedArray()
        val adaptador: ArrayAdapter<String>
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, valores)
    }
}
