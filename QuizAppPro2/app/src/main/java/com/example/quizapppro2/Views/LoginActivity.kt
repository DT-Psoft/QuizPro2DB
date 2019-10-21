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

    //   var editTextUserName = findViewById(R.id.textinp_user_name) as TextInputLayout
        val editTextUserName = findViewById<TextInputEditText>(R.id.textinp_user_name)


        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        val categorias = db.getCategoriesDAO().getAll()


        //----- ESTO SOLO SE HACE UNA VEZ, SI YA LO HICISTE COMENTALO Y DESCOMENTA LO DE ABAJO -------

        //Kike : meto un usuario
       db.UserDAO().InsertUserWithConfig(UserETY("fed", 1, 1))

        //inserto una nueva configuracion (Recuerda que si quieres crear una configuration necesitas pasarle el id del usuario)

        // ----- SI YA HICISTE LO DE ARRIBA SOLO HAZ ESTO Y COMENTA LO DE ARRIBA -------
        AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLogged())
        AppDatabase.setCurrentConfiguration(
            db.User_ConfigurationDAO().getConfigurationByUserId(
                AppDatabase.getCurrentUser().id_user))


        AppDatabase.getLoginUser()
     //   val username2 : Array<UserETY> = db.UserDAO().getAllUsers()

        val btnOpenMenu : Button = findViewById(R.id.btn_login)
        btnOpenMenu.setOnClickListener{
        val username2 = db.UserDAO().getUserByName(editTextUserName.text.toString())
      //      for(i in username2.indices) {
                val login = username2

                if (login == null) {



                }else if (login != null)
                {
                    if(login.is_logged == 0) {
                        login.is_logged = 1

                        val intentMain = Intent(this, MainActivity::class.java)
                        startActivityForResult(intentMain, OPTIONSACTIVITY_REQUEST_CODE)

                        db.User_ConfigurationDAO().AddConfiguration(
                            User_ConfigurationETY(
                                db.UserDAO().getUserByIsLogged().id_user)
                        )
                    }
                    else {
                        login.is_logged = 0
                    }
                }




                if (login.user_name != editTextUserName.text.toString()) {
                    Toast.makeText(
                        this,
                        "Usuario no creado",
                        Toast.LENGTH_SHORT
                    ).show()
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
