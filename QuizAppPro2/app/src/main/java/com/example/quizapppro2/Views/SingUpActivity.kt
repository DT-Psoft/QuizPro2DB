package com.example.quizapppro2.Views

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.quizapppro2.R
import com.google.android.material.textfield.TextInputEditText

class SingUpActivity : AppCompatActivity() {
    lateinit var imageView_ic1: ImageView
    lateinit var imageView_ic2: ImageView
    lateinit var imageView_ic3: ImageView
    lateinit var imageView_ic4: ImageView
    lateinit var imageView_ic5: ImageView
    lateinit var imageView_ic6: ImageView

    lateinit var currentImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        imageView_ic1 = findViewById(R.id.imageView1)
        imageView_ic2 = findViewById(R.id.imageView2)
        imageView_ic3 = findViewById(R.id.imageView3)
        imageView_ic4 = findViewById(R.id.imageView4)
        imageView_ic5 = findViewById(R.id.imageView5)
        imageView_ic6 = findViewById(R.id.imageView6)
        currentImageView = imageView_ic1
        changeSelectedIcon(currentImageView)
        imageView_ic1.setOnClickListener { changeSelectedIcon(imageView_ic1) }
        imageView_ic2.setOnClickListener { changeSelectedIcon(imageView_ic2) }
        imageView_ic3.setOnClickListener { changeSelectedIcon(imageView_ic3) }
        imageView_ic4.setOnClickListener { changeSelectedIcon(imageView_ic4) }
        imageView_ic5.setOnClickListener { changeSelectedIcon(imageView_ic5) }
        imageView_ic6.setOnClickListener { changeSelectedIcon(imageView_ic6) }
        val editTextNewUserName = findViewById<TextInputEditText>(R.id.txtinp_newUser)
        val editTextConfirmUserName = findViewById<TextInputEditText>(R.id.txtinp_confUser)

    }

    @SuppressLint("NewApi")
    private fun changeSelectedIcon(selectedImageView: ImageView) {
        if (currentImageView == selectedImageView) {
            currentImageView.foreground.alpha = 0
            return
        }
        //IMAGEVIEW PREVIO-SELECCIONADO CON OSCURIDAD (OPACACIDAD :)
        currentImageView.foreground.alpha = 255

        //IMAGEVIEW SET NUEVO ICONO

        currentImageView = selectedImageView
        //IMAGEVIEW NUEVO ICONO (IMAGEVIEW) SIN OSCURIDAD
        currentImageView.foreground.alpha = 0
    }
}
