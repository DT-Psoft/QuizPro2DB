package com.example.quizapppro2.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.GameResults
import com.example.quizapppro2.Class.MyListAdapter
import com.example.quizapppro2.Class.Player_list
import com.example.quizapppro2.R


class ScoreboardActivity : AppCompatActivity() {
    lateinit var list: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
        list = findViewById(R.id.score_listview)

        var db: AppDatabase = AppDatabase.getAppDatabase(this)
        var scoreboard = db.ScoreBoardDAO().getAll()
        var player_list : MutableList<Player_list> = mutableListOf()
        for(i in scoreboard.indices){
            var icon = if(scoreboard[i].cheater == 1) R.drawable.ic_joker else null
            var aux = db.UserDAO().getUserById(scoreboard[i].user_id)
            player_list.add(Player_list(aux.user_name, scoreboard[i].score.toString(), aux.image_user, icon))
        }
        val valores = player_list.toTypedArray()
        list.adapter=MyListAdapter(this,valores)


    }
}