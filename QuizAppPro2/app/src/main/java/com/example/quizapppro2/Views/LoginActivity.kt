package com.example.quizapppro2.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY
import com.example.quizapppro2.R
import com.facebook.stetho.Stetho
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.deleteDatabase("quizzapp.db")

    //   var editTextUserName = findViewById(R.id.textinp_user_name) as TextInputLayout
        val editTextUserName = findViewById<TextInputEditText>(R.id.textinp_user_name)


        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        val categorias = db.getCategoriesDAO().getAll()


        //----- ESTO SOLO SE HACE UNA VEZ, SI YA LO HICISTE COMENTALO Y DESCOMENTA LO DE ABAJO -------

        //Kike : meto un usuario
       db.UserDAO().InsertUserWithConfig(UserETY("fede", 1, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("kike", 1, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("cristian", 1, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("kenobi", 1, 1))
        //inserto una nueva configuracion (Recuerda que si quieres crear una configuration necesitas pasarle el id del usuario)
        db.User_ConfigurationDAO().AddConfiguration(
            User_ConfigurationETY(
                db.UserDAO().getUserByIsLogged().id_user)
        )

        // ----- SI YA HICISTE LO DE ARRIBA SOLO HAZ ESTO Y COMENTA LO DE ARRIBA -------
        AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLogged())
        AppDatabase.setCurrentConfiguration(
            db.User_ConfigurationDAO().getConfigurationByUserId(
                AppDatabase.getCurrentUser().id_user))

        AppDatabase.getCurrentUser().is_logged = 0


        AppDatabase.getLoginUser()

        val username2 : Array<UserETY> = db.UserDAO().getAllUsers()

        val btnOpenMenu : Button = findViewById(R.id.btn_login)
        btnOpenMenu.setOnClickListener{
            for(i in username2.indices) {
                val login = username2[i]
                if (login.user_name == editTextUserName.text.toString()) {
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivityForResult(intentMain, OPTIONSACTIVITY_REQUEST_CODE)
                    break
                }
                if (login.user_name != editTextUserName.text.toString()) {
                    Toast.makeText(
                        this,
                        "Usuario incorrecto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        val textRegistrar : TextView = findViewById(R.id.textview_sing_up)
        textRegistrar.setOnClickListener{
            val intentSignUp  = Intent(this, SingUpActivity:: class.java)
            startActivityForResult(intentSignUp,OPTIONSACTIVITY_REQUEST_CODE)
        }





    }
}
