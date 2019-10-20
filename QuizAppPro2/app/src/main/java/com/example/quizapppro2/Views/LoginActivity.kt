package com.example.quizapppro2.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.R
import com.facebook.stetho.Stetho
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sing_up.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    //   var editTextUserName = findViewById(R.id.textinp_user_name) as TextInputLayout
        val editTextUserName = findViewById<TextInputEditText>(R.id.textinp_user_name)


        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        val categorias = db.getCategoriesDAO().getAll()


        //----- ESTO SOLO SE HACE UNA VEZ, SI YA LO HICISTE COMENTALO Y DESCOMENTA LO DE ABAJO -------

        //Kike : meto un usuario
       db.UserDAO().InsertUserWithConfig(UserETY("fede", 1234, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("kike", 1234, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("cristian", 1234, 1))
        db.UserDAO().InsertUserWithConfig(UserETY("kenobi", 1234, 1))
        //inserto una nueva configuracion (Recuerda que si quieres crear una configuration necesitas pasarle el id del usuario)
//        db.User_ConfigurationDAO().AddConfiguration(
//            User_ConfigurationETY(
//                db.UserDAO().getUserByIsLogged().id_user))

        // ----- SI YA HICISTE LO DE ARRIBA SOLO HAZ ESTO Y COMENTA LO DE ARRIBA -------
        AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLogged())
        AppDatabase.setCurrentConfiguration(
            db.User_ConfigurationDAO().getConfigurationByUserId(
                AppDatabase.getCurrentUser().id_user))


        AppDatabase.getLoginUser()

//CONSIGO solo el primer usuario por ahora, no se como arreglarlo DE LA BASE DE DATOS PARA COMPARAR
       val username : String = AppDatabase.getLoginUser().user_name
        val username2 : Array<UserETY> = db.UserDAO().getAllUsers()
        //PRUEBA

//        for(i in username2.indices){
//            Toast.makeText(
//                this,
//                username2[i].user_name,
//                Toast.LENGTH_SHORT
//            ).show()
//        }

        val btnOpenMenu : Button = findViewById(R.id.btn_login)
        btnOpenMenu.setOnClickListener{
            for(i in username2.indices) {
                val login = username2[i]
                if (login.user_name == editTextUserName.text.toString()) {
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivityForResult(intentMain, OPTIONSACTIVITY_REQUEST_CODE)

                    Toast.makeText(
                        this,
                        "Inicio Exitoso",
                        Toast.LENGTH_SHORT
                    ).show()

                    break
                }
                if (login.user_name != editTextUserName.text.toString()) {
                    Toast.makeText(
                        this,
                        "Usuario incorrecto, intente denuevo o registre un usuario",
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
