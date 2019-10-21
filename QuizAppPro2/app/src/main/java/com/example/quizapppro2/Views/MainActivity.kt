package com.example.quizapppro2.Views

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import com.example.quizapppro2.Class.Game_Configuration
import com.facebook.stetho.Stetho

import com.example.quizapppro2.Class.Entities.CategoryETY
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY


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
    private var db: AppDatabase = AppDatabase.getAppDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val builder = AlertDialog.Builder(this)
        val dialogClickListener = DialogInterface.OnClickListener{_,which ->
//            when(which){
//                DialogInterface.BUTTON_POSITIVE ->{btnOpenActivityJuego.setOnClickListener()}
//                DialogInterface.BUTTON_NEGATIVE -> toast("Negative/No button clicked.")
//                DialogInterface.BUTTON_NEUTRAL -> toast("Neutral/Cancel button clicked.")
//            }
        }


        // Set the alert dialog positive/yes button
        builder.setPositiveButton("YES",dialogClickListener)

        // Set the alert dialog negative/no button
        builder.setNegativeButton("NO",dialogClickListener)

        // Set the alert dialog neutral/cancel button
        builder.setNeutralButton("CANCEL",dialogClickListener)

        val btnOpenActivity : Button = findViewById(R.id.btn_start_new_activity)
        btnOpenActivity.setOnClickListener{
            val intentOpciones  = Intent(this, Opciones2:: class.java)
            startActivityForResult(intentOpciones,OPTIONSACTIVITY_REQUEST_CODE)
        }

        val btnOpenActivityJuego : Button = findViewById(R.id.btn_start_game_activity)
        btnOpenActivityJuego.setOnClickListener{

            if(db.LastGameDAO().getIdLastgameByUserIdAndIsActiveNonNull(AppDatabase.getCurrentUser().id_user) == null){
                val intentJuego  = Intent(this, QuizGameActivity:: class.java)
                intentJuego.putExtra("idlastgame",-1)
                startActivity(intentJuego)
            }
            else{
                when(showDialog()){
                    0->{//significa que aceptó
                        var lg = db.LastGameDAO().getIdLastgameIdByUserIdAndIsActive(AppDatabase.getCurrentUser().id_user)
                        val intentJuego  = Intent(this, QuizGameActivity:: class.java)
                        intentJuego.putExtra("idlastgame",lg)
                        startActivity(intentJuego)
                    }
                    1->{
                        var lg = db.LastGameDAO().getLastgameByUserId(AppDatabase.getCurrentUser().id_user)
                        lg.is_active = 0
                        db.LastGameDAO().updateLastGame(lg)
                    }
                    2->{

                    }
                }
            }


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
    private fun showDialog(): Int{
        // Late initialize an alert dialog object

        var aux: Int =0
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Juego en curso")
        builder.setMessage("Parece que tienes un juego en curso, deseas seguir?")
        builder.setPositiveButton("Sí") { dialogInterface: DialogInterface, i: Int ->
            aux = 0
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            aux = 1
        }
        builder.setNeutralButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            aux = 2
        }

        builder.show()

        return aux
    }
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
