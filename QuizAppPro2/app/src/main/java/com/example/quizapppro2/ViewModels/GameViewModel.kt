package com.example.quizapppro2.ViewModels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.R
import com.example.quizapppro2.Class.CategoryClass
import com.example.quizapppro2.Class.Entities.*
import com.example.quizapppro2.Class.QuestionClass
import com.example.quizapppro2.Views.Configuration
import com.example.quizapppro2.Views.Score

class GameViewModel() : ViewModel() {

    var context = AppDatabase.context
    private var db: AppDatabase = AppDatabase.getAppDatabase(context)
    var configuration = db.User_ConfigurationDAO().getConfigurationByUserId(AppDatabase.getCurrentConfiguration().user_id)
    var user: UserETY =AppDatabase.getCurrentUser()
    var scoreboard = ScoreBoardETY(configuration.number_of_questions,user.id_user)
    lateinit var lastgame : LastGameETY
    lateinit var lastgameQuestions: MutableList<LastGame_QuestionETY>
    lateinit var lastgameAnswer: MutableList<LastGame_AnswerETY>

    var currentClue: Int = 0
    var answeredCont: Int = 0
    var currentQuestionState: Boolean = false
    var currentClueString: String = ""
    var currentQuestionClueUsed = false
    var currentQuestion: Int = 0
    var arrayOfPointsValue = arrayOf(3,2,1)
    lateinit var gameQuestionClass : List<QuestionETY>
    lateinit var currentListOfAnswerText : List<String>
    lateinit var currentListOfAnswer : MutableList<AnswerETY>

    //pa saber si el juego terminó
    var gameFinished = false


    var score =  Score.score
    var player =  user
    var pointsValue =  arrayOfPointsValue[configuration.dificulty]

    init {
        gameQuestionClass = getGameQuestions()
        currentListOfAnswerText = getNewListOfCurrentAnswer()
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

        arrayOfQuestions.shuffle()

        return setQuestions(arrayOfQuestions)
    }

    val numOfQuestion get() = configuration.number_of_questions

    val numOfClues get() = configuration.number_of_clues

    fun getCurrentQuestion() = gameQuestionClass[currentQuestion].question_text

    fun getCurrentQuestionObject() = gameQuestionClass[currentQuestion]

    fun getCorretAnswer() = db.AnswerDAO().getCorrectAnswer(gameQuestionClass[currentQuestion].question_id).answer_text

    fun getCurrentQuestionNum() = currentQuestion

    fun getNewListOfCurrentAnswer() : MutableList<String>{
        currentListOfAnswer = db.AnswerDAO().getAnswerByQuestionId(gameQuestionClass[currentQuestion].question_id)
        currentListOfAnswer.shuffle()
        var aux = mutableListOf("1")
        aux.clear()
        for(item in currentListOfAnswer){
            aux.add(item.answer_text)
        }
        currentListOfAnswerText = aux
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
    fun insertLastGame(){
        var aux = db.LastGameDAO().getLastgameByUserId(AppDatabase.getCurrentUser().id_user)
        //en teoria esto deberia eliminar un registro y de no ser así, "algo" seria 0
        if(aux != null){
            db.LastGame_QuestionDAO().deleteLastGameQuestionByIdLastGame(aux.id_lastgame)
        }

        var id_lastgame = db.LastGameDAO().insertLastGame(LastGameETY(
            configuration.dificulty,
            configuration.number_of_questions,
            answeredCont,
            currentQuestion,
            configuration.clues_on,
            configuration.number_of_clues,
            currentClue,
            scoreboard.score,
            user.id_user,
            1
            ))
        Log.d("Hola","${gameQuestionClass.size}")
        for(item in gameQuestionClass){
            var id_lastgamequestion = db.LastGame_QuestionDAO().insertLastGameQuestion(
                LastGame_QuestionETY(
                    if(item.state) 1 else 0,
                    if(item.isCorrect) 1 else 0,
                    item.question_id,
                    item.answered,
                    id_lastgame.toInt()))
        }
    }

    fun setLastGame(){

        lastgame = db.LastGameDAO().getLastgameByUserId(AppDatabase.getCurrentUser().id_user)
        //buscamos el lastgame con el id pasado
        var arrayOfLastGameQuestion = db.LastGame_QuestionDAO().getLastGameQuestionsByLastGameId(lastgame.id_lastgame)
        var aux :MutableList<QuestionETY> = mutableListOf()
        //buscamos las preguntas y las metemos en el array de preguntas del juego
        for(item in arrayOfLastGameQuestion){
            var question = db.QuestionDAO().getQuestionById(item.question_id)
            Log.d("queestion", question.question_text)
            question.isCorrect = item.is_correct == 1
            question.answered = item.answer_by_user
            question.state = item.answered != 0
            aux.add(question)
        }
        gameQuestionClass = aux
        pointsValue = arrayOfPointsValue[configuration.dificulty]

        //seteandotodo lo de lastgame
        configuration.dificulty = lastgame.dificulty
        configuration.number_of_questions = lastgame.number_of_questions
        answeredCont = lastgame.number_of_questions_answered
        currentQuestion = lastgame.current_question
        configuration.clues_on = lastgame.clues_on
        configuration.number_of_clues =lastgame.number_of_clues
        currentClue = lastgame.clues_left
        scoreboard.score = lastgame.current_score

        //var arrayOfLastGameAnswer= db.LastGame_AnswerDAO().getLastGameAnswerByLastGameQuestionId(lastgame.id_lastgame)
    }
    fun setLastGameInactive(){
        lastgame.is_active = 0
        db.LastGameDAO().updateLastGame(lastgame)
    }
    fun insertScoreboard(){
        var aux = ScoreBoardETY(configuration.number_of_questions,AppDatabase.getCurrentUser().id_user)
        aux.score = scoreboard.score
        aux.cheater = scoreboard.cheater

        db.ScoreBoardDAO().insertScoreboard(aux)
    }




}
