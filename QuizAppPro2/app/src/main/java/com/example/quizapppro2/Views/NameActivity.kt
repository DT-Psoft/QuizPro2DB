package com.example.quizapppro2.Views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.quizapppro2.R
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var acceptButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        nameEditText = findViewById(R.id.result_edit_text)
        acceptButton = findViewById(R.id.aceptar_button)

        setResult(Activity.RESULT_CANCELED)
        acceptButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRA_RESULT_TEXT, nameEditText.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
