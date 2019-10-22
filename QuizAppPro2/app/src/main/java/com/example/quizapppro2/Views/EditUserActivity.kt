package com.example.quizapppro2.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import kotlinx.android.synthetic.main.activity_edit_user.*

class EditUserActivity : AppCompatActivity() {
    lateinit var imageView_ic1: ImageView
    lateinit var imageView_ic2: ImageView
    lateinit var imageView_ic3: ImageView
    lateinit var imageView_ic4: ImageView
    lateinit var imageView_ic5: ImageView
    lateinit var imageView_ic6: ImageView
    lateinit var editText_newName: EditText
    lateinit var imageView_prevIcon: ImageView
    var selectedIcon = 0
    lateinit var changeInfoButton: Button
    lateinit var currentImageView: ImageView
    var currentUser = AppDatabase.getAppDatabase(this).UserDAO().getUserByIsLogged()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        imageView_ic1 = findViewById(R.id.imageView1)
        imageView_ic2 = findViewById(R.id.imageView2)
        imageView_ic3 = findViewById(R.id.imageView3)
        imageView_ic4 = findViewById(R.id.imageView4)
        imageView_ic5 = findViewById(R.id.imageView5)
        imageView_ic6 = findViewById(R.id.imageView6)
        imageView_prevIcon = findViewById(R.id.imageView_prevIcon)

        editText_newName = findViewById(R.id.txtinp_newUser)
        changeInfoButton = findViewById(R.id.btn_ChangeInfo)

        editText_newName.setText(currentUser!!.user_name)
        currentImageView = imageView_ic1
        selectedIcon = currentUser!!.image_user
        imageView_prevIcon.setImageResource(currentUser!!.image_user)
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
        btn_ChangeInfo.setOnClickListener {
            validateSinguUp(editText_newName.text.toString())
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
        imageView_prevIcon.setImageResource(selectedIcon)
    }

    private fun validateSinguUp(newUserName: String) {
        var msg = ""
        var db = AppDatabase.getAppDatabase(applicationContext)
        if (newUserName.isBlank() || newUserName.length < 3) {
            msg =
                when {
                    newUserName.isBlank() -> "Porfavor introduzca un nuevo nombre de usuario"
                    newUserName.length < 3 -> "Debes colocar 3 Caracteres en tu nombre"
                    else -> "Revice sus datos, error en formularios"
                }
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        } else if (currentUser!!.user_name == newUserName
            && currentUser!!.image_user == selectedIcon
        ) {

            msg = "Ningun cambio Detectado"
            Toast.makeText(
                this,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (db.UserDAO().getUserByName(newUserName) == null || db.UserDAO().getUserByName(newUserName) == currentUser) {
                currentUser!!.user_name = newUserName
                currentUser!!.image_user = selectedIcon
                db.UserDAO().UpdateUser(currentUser!!)
                msg = "Cambios Exitoso"
                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                msg = "Nombre de Usuario ya es en uso, Intenta con otro nombre"
                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}