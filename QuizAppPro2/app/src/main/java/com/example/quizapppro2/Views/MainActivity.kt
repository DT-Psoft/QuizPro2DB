package com.example.quizapppro2.Views

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import com.example.quizapppro2.Class.Game_Configuration
import com.facebook.stetho.Stetho

import com.example.quizapppro2.Class.Entities.CategoryETY
import com.example.quizapppro2.Class.Entities.LastGameETY
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY
import kotlinx.android.synthetic.main.activity_main.*


const val EXTRA_CONFIGURATION = "com.example.quiz.EXTRA_CONFIGURATION"
const val EXTRA_RESULT_CONFIGURATION = "com.example.quiz.EXTRA_RESULT_CONFIGURATION"

const val OPTIONSACTIVITY_REQUEST_CODE = 1000
const val SCOREACTIVITY_REQUEST_CODE = 2000
const val NAMEACTIVITY_REQUEST_CODE = 3000
const val GAMEACTIVITY_REQUEST_CODE = 4000

val EXTRA_PLAYER_NAME = "com.example.quiz.EXTRA_PLAYER_NAME"
const val EXTRA_RESULT_TEXT = "com.example.quiz.EXTRA_RESULT_TEXT"

class MainActivity : AppCompatActivity() {
    var conf = Game_Configuration()
    lateinit var imageViewUserIcon: ImageView
    lateinit var textViewUserName: TextView
    val db = AppDatabase.getAppDatabase(this)
    var gameInCourseActive = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentUser = db.UserDAO().getUserByIsLogged()
        var userName=currentUser!!.user_name
        var userIcon=currentUser!!.image_user
        val imageButtonButtonOptions: ImageButton = findViewById(R.id.imageButton_options)
        val imageButtonLogout: ImageButton = findViewById(R.id.imageButton_logout)
        textViewUserName = findViewById(R.id.textView_userName)
        imageViewUserIcon = findViewById(R.id.imageView_userIcon)


        AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLoggedNullable())
        AppDatabase.setCurrentConfiguration(
            db.User_ConfigurationDAO().getConfigurationByUserId(
                AppDatabase.getCurrentUser().id_user
            )
        )

        textViewUserName.text=userName
        imageViewUserIcon.setImageResource(userIcon)
        imageButtonLogout.setOnClickListener { onUserLogout(currentUser!!) } //!! = SE QUE DEBE ESTAR LOGGEADO, NO PUEDE DEVOLVER NULL
        val btnOpenActivity: Button = findViewById(R.id.btn_start_new_activity)
        btnOpenActivity.setOnClickListener {
            val intentOpciones = Intent(this, Opciones2::class.java)
            startActivityForResult(intentOpciones, OPTIONSACTIVITY_REQUEST_CODE)
        }

        val btnOpenActivityJuego: Button = findViewById(R.id.btn_start_game_activity)


        btnOpenActivityJuego.setOnClickListener {

            val intentJuego = Intent(this, QuizGameActivity::class.java)
            var aux = db.LastGameDAO().getLastgameByUserId(AppDatabase.getCurrentUser().id_user)
            if(aux == null )
            {
                AppDatabase.lastgameaux = -1
                startActivity(intentJuego)
            }
            else if(aux.is_active == 0){
                AppDatabase.lastgameaux = -1
                startActivity(intentJuego)
            }
            else{

               gameInCourse(aux)
            }

        }
        val btnOpenActivityScoreboard: Button = findViewById(R.id.btn_start_scoreboard_activity)
        btnOpenActivityScoreboard.setOnClickListener {
            val intentscore = Intent(this, LeaderboardActivity::class.java)
            startActivityForResult(intentscore, SCOREACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SCOREACTIVITY_REQUEST_CODE -> {
            }
            GAMEACTIVITY_REQUEST_CODE -> {
            }
            OPTIONSACTIVITY_REQUEST_CODE -> {
            }

        }
    }

    private fun onUserLogout(CurrUser: UserETY) {

        val alertDialog = AlertDialog.Builder(this)
            //set icon
            .setIcon(R.drawable.ic_logout)
            //set title
            .setTitle("¿Estas seguro de que deseas CERRAR SEISON?")
            //set message
            .setMessage("Al confirmar la se Cerrara tu Sesion Actual")
            //set positive button
            .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, i ->
                //set what would happen when positive button is clicked
                CurrUser.is_logged = 0
                db.UserDAO().UpdateUser(CurrUser)
                if (db.UserDAO().getUserByIsLogged()== null){
                    finish()
                }else Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            })
            //set negative button
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                //set what should happen when negative button is clicked
                Toast.makeText(applicationContext, "Ninguna Accion", Toast.LENGTH_LONG).show()
            })
            .show()

    }
    private fun gameInCourse(lastGame: LastGameETY){

        val intentJuego = Intent(this, QuizGameActivity::class.java)
        var aux = -1
        val alertDialog = AlertDialog.Builder(this)
            //set icon
            .setIcon(R.drawable.ic_logout)
            //set title
            .setTitle("Juego en curso")
            //set message
            .setMessage("Parece que tienes un juego en curso, ¿Deseas continuar?")
            //set positive button
            .setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, i ->

                AppDatabase.lastgameaux = lastGame.id_lastgame
                intentJuego.putExtra("idlastgame", lastGame.id_lastgame)
                startActivity(intentJuego)

            })
            //set negative button
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                AppDatabase.lastgameaux = -1
                lastGame.is_active = 0
                db.LastGameDAO().updateLastGame(lastGame)
                Toast.makeText(applicationContext, "Nuevo juego", Toast.LENGTH_LONG).show()
                startActivity(intentJuego)
            })
            .show()

    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            //set icon
            .setIcon(R.drawable.ic_warning_sign)
            //set title
            .setTitle("¿Estas seguro de que deseas SALIR?")
            //set message
            .setMessage("Al confirmar la App se Cerrara")
            //set positive button
            .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, i ->
                //set what would happen when positive button is clicked
                finishAffinity()
            })
            //set negative button
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                //set what should happen when negative button is clicked
                Toast.makeText(applicationContext, "Ninguna Accion", Toast.LENGTH_LONG).show()
            })
            .show()
    }
}

