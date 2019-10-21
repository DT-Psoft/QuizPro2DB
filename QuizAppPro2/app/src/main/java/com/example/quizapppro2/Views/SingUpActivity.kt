package com.example.quizapppro2.Views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.DAO.UserDAO
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.R
import com.google.android.material.textfield.TextInputEditText

class SingUpActivity : AppCompatActivity() {
    lateinit var imageView_ic1: ImageView
    lateinit var imageView_ic2: ImageView
    lateinit var imageView_ic3: ImageView
    lateinit var imageView_ic4: ImageView
    lateinit var imageView_ic5: ImageView
    lateinit var imageView_ic6: ImageView
    var selectedIcon = 0
    lateinit var signupButton: Button
    lateinit var currentImageView: ImageView
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        imageView_ic1 = findViewById(R.id.imageView1)
        imageView_ic2 = findViewById(R.id.imageView2)
        imageView_ic3 = findViewById(R.id.imageView3)
        imageView_ic4 = findViewById(R.id.imageView4)
        imageView_ic5 = findViewById(R.id.imageView5)
        imageView_ic6 = findViewById(R.id.imageView6)
        signupButton = findViewById(R.id.btn_Signup)
        val editTextNewUserName = findViewById<TextInputEditText>(R.id.txtinp_newUser)
        val editTextConfirmUserName = findViewById<TextInputEditText>(R.id.txtinp_confUser)
        currentImageView = imageView_ic1
        currentImageView.foreground.alpha = 0
        selectedIcon = R.drawable.ic_bullbasaur

        imageView_ic1.setOnClickListener {
            changeSelectedIcon(
                imageView_ic1,
                R.drawable.ic_bullbasaur
            )
        }
        imageView_ic2.setOnClickListener {
            changeSelectedIcon(
                imageView_ic2,
                R.drawable.ic_charmander
            )
        }
        imageView_ic3.setOnClickListener { changeSelectedIcon(imageView_ic3, R.drawable.ic_mankey) }
        imageView_ic4.setOnClickListener {
            changeSelectedIcon(
                imageView_ic4,
                R.drawable.ic_pikachu
            )
        }
        imageView_ic5.setOnClickListener {
            changeSelectedIcon(
                imageView_ic5,
                R.drawable.ic_snorlax
            )
        }
        imageView_ic6.setOnClickListener {
            changeSelectedIcon(
                imageView_ic6,
                R.drawable.ic_squirtle
            )
        }
        signupButton.setOnClickListener {
            validateSinguUp(
                editTextNewUserName.text.toString(),
                editTextConfirmUserName.text.toString()

            )
        }


    }

    @SuppressLint("NewApi")
    private fun changeSelectedIcon(selectedImageView: ImageView, idResource: Int) {
        if (currentImageView == selectedImageView && selectedIcon == idResource) return

        //IMAGEVIEW PREVIO-SELECCIONADO CON OSCURIDAD (OPACACIDAD :)
        currentImageView.foreground.alpha = 255

        //IMAGEVIEW SET NUEVO ICONO

        currentImageView = selectedImageView
        //IMAGEVIEW NUEVO ICONO (IMAGEVIEW) SIN OSCURIDAD
        currentImageView.foreground.alpha = 0
        selectedIcon = idResource


    }

    private fun validateSinguUp(userName: String, confirmUserName: String) {
        if (userName != confirmUserName || userName.length < 3) {
            var msg =
                when {
                    userName != confirmUserName -> "El nombre de usuario no concuerda"
                    userName.length < 3 -> "Debes colocar 3 Caracteres en tu nombre"
                    else -> "Rebice sus datos, error en formularios"
                }
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val db = AppDatabase.getAppDatabase(this)
            var msg =
                if (db.UserDAO().InsertUserWithConfig(
                        UserETY(
                            userName,
                            0,
                            selectedIcon
                        )
                    )
                ) "USUARIO REGISTRADO" else "ERROR:USUARIO REGISTRADO, inserte otro nombre"
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
