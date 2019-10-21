package com.example.quizapppro2.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.DAO.UserDAO
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY
import com.example.quizapppro2.R
import com.facebook.stetho.Stetho
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //this.deleteDatabase("quizzapp.db")

        val editTextUserName = findViewById<TextInputEditText>(R.id.textinp_user_name)


        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)



        //----- ESTO SOLO SE HACE UNA VEZ, SI YA LO HICISTE COMENTALO Y DESCOMENTA LO DE ABAJO -------

        //Kike : meto un usuario
        db.UserDAO().InsertUserWithConfig(UserETY("FED", 1, 2131230842))
        db.UserDAO().InsertUserWithConfig(UserETY("QWE", 0, 2131230842))

        //inserto una nueva configuracion (Recuerda que si quieres crear una configuration necesitas pasarle el id del usuario)

        // ----- SI YA HICISTE LO DE ARRIBA SOLO HAZ ESTO Y COMENTA LO DE ARRIBA -------
        AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLogged() as UserETY)
        AppDatabase.setCurrentConfiguration(
            db.User_ConfigurationDAO().getConfigurationByUserId(
                AppDatabase.getCurrentUser().id_user))


//        AppDatabase.getLoginUser()


        val btnOpenMenu : Button = findViewById(R.id.btn_login)
        btnOpenMenu.setOnClickListener{
        val username2 : UserETY? = db.UserDAO().getUserByName(editTextUserName.text.toString())
      //      for(i in username2.indices) {
                val login = username2


            var useridLogged = db.UserDAO().getUserByIsLogged() as UserETY
            var userNameLogged = db.UserDAO().getUserByName(editTextUserName.text.toString()) as UserETY


          //  useridLogged.user_name = "ooo"
         //   useridLogged.is_logged = 0

          //  userNameLogged.is_logged = 1

        //    db.UserDAO().UpdateUser(useridLogged)
        //    db.UserDAO().UpdateUser(userNameLogged)

            db.UserDAO().UpdateUser(useridLogged)
                if (login == null) {

                    Toast.makeText(
                        this,
                        "Datos incorrectos",
                        Toast.LENGTH_SHORT
                    ).show()
                  //  userNameLogged.is_logged = 0
                    useridLogged.is_logged = 0
                    db.UserDAO().UpdateUser(useridLogged)
                    db.UserDAO().UpdateUser(userNameLogged)

                }else
                {
                    //useridLogged.is_logged = 1
                    userNameLogged.is_logged = 1
                    db.UserDAO().UpdateUser(useridLogged)
                    db.UserDAO().UpdateUser(userNameLogged)


                        val intentMain = Intent(this, MainActivity::class.java)
                        startActivityForResult(intentMain, OPTIONSACTIVITY_REQUEST_CODE)


                }
        //    }

        }

        val textRegistrar : TextView = findViewById(R.id.textview_sing_up)
        textRegistrar.setOnClickListener{
            val intentSignUp  = Intent(this, SingUpActivity:: class.java)
            startActivityForResult(intentSignUp,OPTIONSACTIVITY_REQUEST_CODE)
        }





    }
}
