package com.example.quizapppro2.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import com.example.quizapppro2.Class.Game_Configuration
import com.facebook.stetho.Stetho


const val EXTRA_CONFIGURATION= "com.example.quiz.EXTRA_CONFIGURATION"
const val EXTRA_RESULT_CONFIGURATION = "com.example.quiz.EXTRA_RESULT_CONFIGURATION"

const val OPTIONSACTIVITY_REQUEST_CODE = 1000
const val SCOREACTIVITY_REQUEST_CODE = 2000
const val NAMEACTIVITY_REQUEST_CODE = 3000
const val GAMEACTIVITY_REQUEST_CODE = 4000

val EXTRA_PLAYER_NAME = "com.example.quiz.EXTRA_PLAYER_NAME"
const val EXTRA_RESULT_TEXT = "com.example.quiz.EXTRA_RESULT_TEXT"

class MainActivity : AppCompatActivity() {

    var conf = Game_Configuration()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
       // val db = AppDatabase.getAppDatabase(this)


        val btnOpenActivity : Button = findViewById(R.id.btn_start_new_activity)
        btnOpenActivity.setOnClickListener{
            val intentOpciones  = Intent(this, Opciones2:: class.java)
            startActivityForResult(intentOpciones,OPTIONSACTIVITY_REQUEST_CODE)
        }

        val btnOpenActivityJuego : Button = findViewById(R.id.btn_start_game_activity)
        btnOpenActivityJuego.setOnClickListener{
            val intentJuego  = Intent(this, QuizGameActivity:: class.java)
            startActivityForResult(intentJuego, GAMEACTIVITY_REQUEST_CODE)
        }
        val btnOpenActivityScoreboard: Button = findViewById(R.id.btn_start_scoreboard_activity)
        btnOpenActivityScoreboard.setOnClickListener{
            val intentscore  = Intent(this, LeaderboardActivity:: class.java)
            startActivityForResult(intentscore, SCOREACTIVITY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            SCOREACTIVITY_REQUEST_CODE ->{}
            GAMEACTIVITY_REQUEST_CODE ->{}
            OPTIONSACTIVITY_REQUEST_CODE->{}

        }
    }


}
