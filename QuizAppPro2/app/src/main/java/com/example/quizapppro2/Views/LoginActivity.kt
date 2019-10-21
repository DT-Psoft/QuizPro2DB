package com.example.quizapppro2.Views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.R
import com.facebook.stetho.Stetho
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //this.deleteDatabase("quizzapp.db")

        val editTextUserName: TextInputEditText = findViewById(R.id.textinp_user_name)


        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)


//        AppDatabase.getLoginUser()
        val btnOpenMenu: Button = findViewById(R.id.btn_login)
        if (db.UserDAO().getUserByIsLogged() != null) {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
        btnOpenMenu.setOnClickListener {

            val username2: UserETY? = db.UserDAO().getUserByName(editTextUserName.text.toString())
            //      for(i in username2.indices) {
            val login = username2
            var useridLogged = db.UserDAO().getUserByIsLogged()

            if (login == null) {
                Toast.makeText(
                    this,
                    "Datos incorrectos",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                //useridLogged.is_logged = 1
                if (useridLogged == null) {
                    login.is_logged = 1
                    db.UserDAO().UpdateUser(login)
                } else {
                    useridLogged.is_logged = 0
                    login.is_logged = 1
                    db.UserDAO().UpdateUser(useridLogged)
                    db.UserDAO().UpdateUser(login)
                }
                AppDatabase.setCurrentUser(db.UserDAO().getUserByIsLoggedNullable())
                AppDatabase.setCurrentConfiguration(
                    db.User_ConfigurationDAO().getConfigurationByUserId(
                        AppDatabase.getCurrentUser().id_user
                    )
                )
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
        }

        val textRegistrar: Button = findViewById(R.id.textview_sing_up)
        textRegistrar.setOnClickListener {

            val intentSignUp = Intent(applicationContext, SingUpActivity::class.java)
            startActivity(intentSignUp)

        }


    }
}
