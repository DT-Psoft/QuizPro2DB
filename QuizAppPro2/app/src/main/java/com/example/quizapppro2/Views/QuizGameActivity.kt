package com.example.quizapppro2.Views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.GameResults
import com.example.quizapppro2.ViewModels.GameViewModel
import com.example.quizapppro2.R


class QuizGameActivity : AppCompatActivity() {

    val db = AppDatabase.getAppDatabase(this)

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }

    private val vm by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory { GameViewModel(this) }
        ).get(GameViewModel::class.java)
    }

    private lateinit var nextButton: Button
    private lateinit var previousButton: Button

    private lateinit var answersButtons: Array<Button>

    private lateinit var ansOneButton: Button
    private lateinit var ansTwoButton: Button
    private lateinit var ansThreeButton: Button
    private lateinit var ansFourButton: Button

    private lateinit var questionTextView: TextView
    private lateinit var questionNumTextView: TextView
    private lateinit var questionContTextView: TextView
    private lateinit var cluesNumTextView: TextView

    private lateinit var currentQuestion: String
    private var cluesOnThisQuestion = true

    private var score: Int = 0
    private var cont: Int = 0
    private var currentNumOfButtonsAvailables = when(vm.configuration.dificulty){
        0->5
        1->4
        2->3
        else->0
    }
    private fun updateQuestion() {
        var listCurrentAnswer = vm.currentListOfAnswer as MutableList
        if(vm.currentQuestionClueUsed){
            answersButtons[vm.currentListOfAnswer.indexOf(vm.currentClueString)].isEnabled = false
            vm.currentQuestionClueUsed = true
        }
        else{
            listCurrentAnswer= vm.getNewListOfCurrentAnswers()
        }
        currentNumOfButtonsAvailables = when(vm.configuration.dificulty){
            0->5
            1->4
            2->3
            else->0
        }
        cluesOnThisQuestion = true

        //esto es un swap de posicion
        var positionOfCorrectAnswer = getRandomNumber()
        var aux = listCurrentAnswer[positionOfCorrectAnswer]
        var swapPosition = listCurrentAnswer.indexOfFirst{x -> x == vm.getCorretAnswer()}
        listCurrentAnswer[positionOfCorrectAnswer] = vm.getCorretAnswer()
        listCurrentAnswer[swapPosition] = aux

        when(vm.configuration.dificulty){
            0-> {
                ansOneButton.setText(listCurrentAnswer[0])
                ansTwoButton.setText(listCurrentAnswer[1])
                ansThreeButton.setText(listCurrentAnswer[2])
                ansFourButton.setText(listCurrentAnswer[3])
            }
            1-> {ansFourButton.isGone = true
                ansOneButton.setText(listCurrentAnswer[0])
                ansTwoButton.setText(listCurrentAnswer[1])
                ansThreeButton.setText(listCurrentAnswer[2])
            }
            2-> {ansFourButton.isGone = true
                ansThreeButton.isGone = true
                ansOneButton.setText(listCurrentAnswer[0])
                ansTwoButton.setText(listCurrentAnswer[1])}
        }

        if(vm.getCurrentQuestionObject().state) {
            var aux = answersButtons.toList()
            aux.forEach{x -> x.isEnabled = false}

        }
        questionTextView.setText(vm.getCurrentQuestion())
        questionContTextView.text =
            "Pregunta: ${vm.getCurrentQuestionNum() + 1}/${vm.numOfQuestion}"
        questionNumTextView.text = "Pregunta: ${vm.getCurrentQuestionNum() + 1}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        nextButton = findViewById(R.id.nxt_Btn)
        previousButton = findViewById(R.id.prev_Btn)

        ansOneButton = findViewById(R.id.answ1_btn)
        ansTwoButton = findViewById(R.id.answ2_btn)
        ansThreeButton = findViewById(R.id.answ3_btn)
        ansFourButton = findViewById(R.id.answ4_btn)
        answersButtons = arrayOf(ansOneButton, ansTwoButton, ansThreeButton, ansFourButton)

        questionTextView = findViewById(R.id.quesTl_text)
        questionContTextView = findViewById(R.id.quesCont_text)
        questionNumTextView = findViewById(R.id.quesNum_text)
        cluesNumTextView = findViewById(R.id.pistas_counter)

        cluesNumTextView.isEnabled =( (vm.configuration.clues_on == 1) && (vm.currentClue < vm.configuration.number_of_clues) ) || vm.currentQuestionClueUsed
        cluesNumTextView.text = if(vm.configuration.clues_on == 1) "Pistas: ${vm.currentClue}/${vm.numOfClues}" else "Pistas: 0/0"
        updateQuestion()
        cluesOnThisQuestion = !vm.currentQuestionClueUsed
        nextButton.setOnClickListener() {
            vm.nextQuestion()
            updateQuestion()
            buttonsAreEnabled(!vm.currentQuestionState)
        }
        previousButton.setOnClickListener() {
            vm.previousQuestion()
            updateQuestion()
            buttonsAreEnabled(!vm.currentQuestionState)
        }
        //Botones de Posibles respuestas
        ansOneButton.setOnClickListener() {
            onAnswered(ansOneButton.text as String)
        }
        ansTwoButton.setOnClickListener() {
            onAnswered(ansTwoButton.text as String)
        }
        ansThreeButton.setOnClickListener() {
            onAnswered(ansThreeButton.text as String)
        }
        ansFourButton.setOnClickListener() {
            onAnswered(ansFourButton.text as String)
        }

        cluesNumTextView.setOnClickListener{

            if(cluesOnThisQuestion && !vm.getCurrentQuestionObject().state){
                currentNumOfButtonsAvailables--
                var rnds = getRandomNumber()
                var posOfAnswer = getPositionOfCorrectAnswer()
                while(rnds == posOfAnswer) {
                    rnds = getRandomNumber()
                }
                vm.currentClueString = vm.currentListOfAnswer[rnds]
                if(vm.currentClue < vm.configuration.number_of_clues){
                    vm.currentClue++
                }
                if(vm.currentClue == vm.numOfClues){
                    cluesNumTextView.isEnabled = false
                }
                vm.currentQuestionClueUsed = true
                cluesNumTextView.text = "Pistas: ${vm.currentClue}/${vm.numOfClues}"
                answersButtons[rnds].isEnabled = false
                cluesOnThisQuestion = false
                vm.setPlayerCheater()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            NAMEACTIVITY_REQUEST_CODE ->{
                when(resultCode){
                    Activity.RESULT_CANCELED ->{
                        finish()
                        Toast.makeText(this, "Juego cancelado", Toast.LENGTH_SHORT).show()
                    }
                    Activity.RESULT_OK ->{
                        vm.player.user_name= if(data?.getStringExtra(EXTRA_RESULT_TEXT).toString() == "")
                            vm.player.user_name
                        else data?.getStringExtra(EXTRA_RESULT_TEXT).toString()

                        vm.score.AddPlayer()
                        val intentScore = Intent(this, LeaderboardActivity::class.java)
                        startActivityForResult(intentScore, SCOREACTIVITY_REQUEST_CODE)
                    }
                }
            }
        }
    }

    fun onAnswered(answered: String) {
        vm.setAnsweredAt(vm.getCurrentQuestionNum(), answered)
        buttonsAreEnabled(false)
        if (vm.answeredCont == vm.numOfQuestion) {
            val intentName = Intent(this, NameActivity::class.java)
            startActivityForResult(intentName, NAMEACTIVITY_REQUEST_CODE)
        }
    }

    fun buttonsAreEnabled(enabled: Boolean) {
        ansOneButton.isEnabled = enabled
        ansTwoButton.isEnabled = enabled
        ansThreeButton.isEnabled = enabled
        ansFourButton.isEnabled = enabled
    }

    fun getPositionOfCorrectAnswer() : Int{
        return vm.currentListOfAnswer.indexOf(vm.getCorretAnswer())
    }

    fun getRandomNumber(): Int = when(vm.configuration.dificulty){
            0-> (0..3).random()
            1-> (0..2).random()
            2 -> (0..1).random()
            else -> -1
    }

}

object Score {
    var score = GameResults()
}