package com.example.quizapppro2.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapppro2.R
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.google.android.material.textfield.TextInputEditText

class SingUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val editTextNewUserName = findViewById<TextInputEditText>(R.id.txtinp_newUser)
        val editTextConfirmUserName = findViewById<TextInputEditText>(R.id.txtinp_confUser)

        //AUN NO SE COMO VA ESTA
      //  val username : Unit = AppDatabase.setNewUser(UserETY(user_name = editTextNewUserName.text.toString(), prueba, prueba, prueba))






    }
}
