package com.example.quizapppro2.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.CheckBox
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Game_Configuration
import com.example.quizapppro2.R
import com.example.quizapppro2.ViewModels.GameViewModel
import com.example.quizapppro2.ViewModels.OptionsViewModel

object Configuration {
    var conf = Game_Configuration()
}

class Opciones2 : AppCompatActivity() {

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }

    private val vmO by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory { OptionsViewModel(this) }
        ).get(OptionsViewModel::class.java)
    }

    private lateinit var todosCheckBox: CheckBox
    private lateinit var cineCheckBox: CheckBox
    private lateinit var historiaCheckBox: CheckBox
    private lateinit var matematicasCheckBox: CheckBox
    private lateinit var fisicaCheckBox: CheckBox
    private lateinit var na1CheckBox: CheckBox
    private lateinit var na2CheckBox: CheckBox

    private lateinit var radioGroupDificultad: RadioGroup
    private lateinit var bajaRadioButton: RadioButton
    private lateinit var mediaRadioButton: RadioButton
    private lateinit var altaRadioButton: RadioButton

    private lateinit var pistasSwitch: Switch

    private lateinit var questionSpinner: AbsSpinner
    private lateinit var cheatsSpinner: AbsSpinner

    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones2)


        todosCheckBox = findViewById(R.id.todos_check)
        cineCheckBox = findViewById(R.id.cine_check)
        historiaCheckBox = findViewById(R.id.historia_check)
        matematicasCheckBox = findViewById(R.id.matematicas_check)
        fisicaCheckBox = findViewById(R.id.fisica_check)
        na1CheckBox = findViewById(R.id.na1_check)
        na2CheckBox = findViewById(R.id.na2_check)

        radioGroupDificultad = findViewById(R.id.radio_group_dificultad)
        bajaRadioButton = findViewById(R.id.baja_radio)
        mediaRadioButton = findViewById(R.id.media_radio)
        altaRadioButton = findViewById(R.id.alta_radio)

        pistasSwitch = findViewById(R.id.pistas_switch)
        questionSpinner = findViewById(R.id.numbers_quest_spinner)
        cheatsSpinner = findViewById(R.id.numbers_cheat_no_spinner)

        saveButton= findViewById(R.id.guardar_button)

   //   Categorias CheckBox logica

        todosCheckBox.isChecked = vmO.configuration.all_categories == 1
        na1CheckBox.isChecked = vmO.categories[0]
        fisicaCheckBox.isChecked = vmO.categories[1]
        cineCheckBox.isChecked = vmO.categories[2]
        matematicasCheckBox.isChecked = vmO.categories[3]
        historiaCheckBox.isChecked = vmO.categories[4]
        na2CheckBox.isChecked = vmO.categories[5]

        todosCheckBox.setOnCheckedChangeListener { _, ischecked ->
            vmO.configuration.all_categories = if(ischecked) 1 else 0
            if (ischecked){
                cineCheckBox.isChecked = true
                historiaCheckBox.isChecked = true
                matematicasCheckBox.isChecked = true
                fisicaCheckBox.isChecked = true
                na1CheckBox.isChecked = true
                na2CheckBox.isChecked = true
            }
        }
        na1CheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(0, na1CheckBox)
        }
        fisicaCheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(1, fisicaCheckBox)
        }
        cineCheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(2, cineCheckBox)
        }
        matematicasCheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(3, matematicasCheckBox)
        }
        historiaCheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(4, historiaCheckBox)
        }
        na2CheckBox.setOnCheckedChangeListener { _, ischecked ->
            onCheckedChangeListener(5, na2CheckBox)
        }

//        Numero de preguntas

        var adapter: ArrayAdapter<Int> =
            ArrayAdapter<Int>(
                this,
                android.R.layout.simple_spinner_item,
                vmO.arrayQuestionsNumber
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        questionSpinner.adapter = adapter

        questionSpinner.setSelection(if(vmO.categoriesNumber != 1) vmO.configuration.number_of_questions - 5 else 0)

        questionSpinner.isEnabled = vmO.categoriesNumber != 1

        questionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                vmO.configuration.number_of_questions=
                    questionSpinner.selectedItem.toString().toInt()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
//          Dificultad
        var idCurrentRadio = when (vmO.configuration.dificulty) {
            0 -> altaRadioButton.id
            1 -> mediaRadioButton.id
            2 -> bajaRadioButton.id
            else -> -1
        }
        radioGroupDificultad.check(idCurrentRadio)
        radioGroupDificultad.setOnCheckedChangeListener { _, _ ->
            when (radioGroupDificultad.checkedRadioButtonId) {
                altaRadioButton.id -> vmO.configuration.dificulty = 0
                mediaRadioButton.id -> vmO.configuration.dificulty = 1
                bajaRadioButton.id -> vmO.configuration.dificulty = 2
            }
        }

//          Habilitar pistas
        pistasSwitch.isChecked = vmO.configuration.clues_on == 1
        pistasSwitch.setOnCheckedChangeListener { _, ischecked ->
            vmO.configuration.clues_on = if(ischecked) 1 else 0
            Toast.makeText(
                this,
                if (ischecked) "Pistas activadas" else "Pistas desactivadas",
                Toast.LENGTH_SHORT
            ).show()
            cheatsSpinner.isEnabled = vmO.configuration.clues_on == 1
            if (!ischecked) {
                vmO.configuration.number_of_clues = 1
            }
        }

//          Numero de pistas
        var adapter1: ArrayAdapter<Int> =
            ArrayAdapter<Int>(
                this,
                android.R.layout.simple_spinner_item,
                vmO.arrayCluesNumber
            )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)

        //si las pistas no estan activas, pues esto tampoco
        cheatsSpinner.isEnabled = vmO.configuration.clues_on == 1
        cheatsSpinner.adapter = adapter1
        cheatsSpinner.setSelection(vmO.configuration.number_of_clues - 1)

        cheatsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                vmO.configuration.number_of_clues= cheatsSpinner.selectedItem.toString().toInt()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        saveButton.setOnClickListener{
            vmO.updateOptions()
        }
    }
    private fun onCheckedChangeListener(pos: Int, checkBox: CheckBox) {
        if (vmO.categoriesNumber == 1 && !checkBox.isChecked){
            checkBox.isChecked = true

        } else {
            questionSpinner.isEnabled = true
            vmO.setEnabledCategory(pos, checkBox.isChecked)
            todosCheckBox.isChecked = vmO.categoriesNumber == 6
            if(vmO.categoriesNumber == 1){
                questionSpinner.isEnabled = false
                questionSpinner.setSelection(0)
            }
        }

    }

    fun onRadioButtonClick(view: View) {
        var string: String = when (vmO.configuration.dificulty) {
            0 -> "Dificultad alta"
            1 -> "Dificultad media"
            2 -> "Dificultad baja"
            else -> ""
        }
        Toast.makeText(
            this,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}
