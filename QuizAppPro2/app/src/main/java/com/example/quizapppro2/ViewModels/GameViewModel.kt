package com.example.quizapppro2.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import com.example.quizapppro2.Class.CategoryClass
import com.example.quizapppro2.Class.Entities.*
import com.example.quizapppro2.Class.QuestionClass
import com.example.quizapppro2.Views.Configuration
import com.example.quizapppro2.Views.Score

class GameViewModel(context: Context) : ViewModel() {

    lateinit var context: Context
    private var db: AppDatabase =  AppDatabase.getAppDatabase(context)
    var configuration = AppDatabase.getCurrentConfiguration()
    var user: UserETY =AppDatabase.getCurrentUser()
    var scoreboard = ScoreBoardETY(configuration.number_of_questions,user.id_user)

    var currentClue: Int = 0
    var answeredCont: Int = 0
    var currentQuestionState: Boolean = false
    var currentClueString: String = ""
    var currentQuestionClueUsed = false
    var currentQuestion: Int = 0
    var arrayOfPointsValue = arrayOf(3,2,1)
    lateinit var gameQuestionClass : List<QuestionETY>
    lateinit var currentListOfAnswer : List<String>

    var score =  Score.score
    var player =  user
    var pointsValue =  arrayOfPointsValue[configuration.dificulty]

    init {
        gameQuestionClass = getGameQuestions()
        currentListOfAnswer = getNewListOfCurrentAnswers()
    }

    private fun setQuestions(list: MutableList<CategoryETY>): MutableList<QuestionETY> {

        val arrayOfQuestion: MutableList<QuestionETY> = mutableListOf()
        var auxList: MutableList<QuestionETY>
        var contOfIteration = 0
        var contAux = 0

        while (contOfIteration < configuration.number_of_questions) {

            if (contAux >= list.size){
                contAux = 0
            }
            auxList = db.QuestionDAO().getQuestionsByCategoryId(list[contAux].id_category) as MutableList<QuestionETY>
            auxList.shuffle()
            var cont = 0
            while(arrayOfQuestion.firstOrNull{ x -> x.question_text == auxList[cont].question_text} != null){
                cont++
            }
            arrayOfQuestion.add(auxList[cont])
            contOfIteration++
            contAux++
        }
        return arrayOfQuestion
    }

    fun getGameQuestions(): MutableList<QuestionETY> {
        val arrayOfQuestions: MutableList<CategoryETY> = mutableListOf()
        for ((indice, value) in configuration.categories_selected.withIndex()) {
            if (value == '1') {
                //mete las índices de las categorias que se van a utilizar en el juego
                arrayOfQuestions.add(db.CategoryDAO().getCategoryById(indice+1))
            }
        }
//        val finalQuest: MutableList<CategoryETY> = mutableListOf()
//        for ((indice, value) in arrayOfQuestions.withIndex()) {
//            finalQuest.add(db.CategoryDAO().getCategoryById(indice))
//        }
        arrayOfQuestions.shuffle()

        return setQuestions(arrayOfQuestions)
    }

    val numOfQuestion get() = configuration.number_of_questions

    val numOfClues get() = configuration.number_of_clues

    fun getCurrentQuestion() = gameQuestionClass[currentQuestion].question_text

    fun getCurrentQuestionObject() = gameQuestionClass[currentQuestion]

    fun getCorretAnswer() = db.AnswerDAO().getCorrectAnswer(gameQuestionClass[currentQuestion].question_id).answer_text

    fun getCurrentQuestionNum() = currentQuestion

    fun getNewListOfCurrentAnswers() : MutableList<String>{
        var aux = db.AnswerDAO().getAnswerTextByCategoryId(gameQuestionClass[currentQuestion].question_id)
        aux.shuffle()
        currentListOfAnswer = aux
        return aux
    }

    fun setAnsweredAt(pos: Int, string:String){
        gameQuestionClass[getCurrentQuestionNum()].isCorrect = string == db.AnswerDAO().getCorrectAnswer(gameQuestionClass[getCurrentQuestionNum()].question_id).answer_text
        gameQuestionClass[getCurrentQuestionNum()].answered = string
        if(gameQuestionClass[getCurrentQuestionNum()].isCorrect) {
          //  player.numOfQuestionCorrect++
            scoreboard.score += pointsValue
        }
        gameQuestionClass[pos].state = true
        answeredCont++
    }

    fun setPlayerCheater(){
        scoreboard.cheater = 1
    }

    fun nextQuestion() {
        currentQuestion = (currentQuestion + 1) % gameQuestionClass.size
        currentQuestionState = gameQuestionClass[currentQuestion].state
        currentQuestionClueUsed = false    }

    fun previousQuestion() {
        currentQuestion = (currentQuestion + gameQuestionClass.size - 1) % gameQuestionClass.size
        currentQuestionState = gameQuestionClass[currentQuestion].state
        currentQuestionClueUsed = false
    }




}
