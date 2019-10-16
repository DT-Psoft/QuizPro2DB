package com.example.quizapppro2.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quizapppro2.Class.Category
import com.example.quizapppro2.Class.Question
import com.example.quizapppro2.Views.Configuration
import com.example.quizapppro2.Views.Score

class GameViewModel(context: Context) : ViewModel() {

    var conf = Configuration.conf

    var currentClue: Int = 0
    var answeredCont: Int = 0
    var currentQuestionState: Boolean = false
    var currentClueString: String = ""
    var currentQuestionClueUsed = false
    var currentQuestion: Int = 0
    lateinit var gameQuestion : List<Question>
    lateinit var currentListOfAnswer : List<String>

    var score =  Score.score
    var player =  Score.score.currentPlayer
    var pointsValue =  conf.getPointsValue()

   /* init {
        conf.categoriesList =
            listOf(
                Category(
                    0,
                    context.resources.getStringArray(R.array.quest_cine),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_cine)[0],
                            context.resources.getStringArray(R.array.ans_cine1),
                            "22"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_cine)[1],
                            context.resources.getStringArray(R.array.ans_cine2),
                            "The Hateful Eight"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_cine)[2],
                            context.resources.getStringArray(R.array.ans_cine3),
                            "Jurassic Park"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_cine)[3],
                            context.resources.getStringArray(R.array.ans_cine4),
                            "Pixar"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_cine)[4],
                            context.resources.getStringArray(R.array.ans_cine5),
                            "Terminator"
                        )

                    )
                ),
                Category(
                    1, context.resources.getStringArray(R.array.quest_historia),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_historia)[0],
                            context.resources.getStringArray(R.array.ans_historia),
                            "Egipcios"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_historia)[1],
                            context.resources.getStringArray(R.array.ans_historia1),
                            "XIV"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_historia)[2],
                            context.resources.getStringArray(R.array.ans_historia3),
                            "1821"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_historia)[3],
                            context.resources.getStringArray(R.array.ans_historia4),
                            "Imperio Britanico"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_historia)[4],
                            context.resources.getStringArray(R.array.ans_historia5),
                            "1917"
                        )

                    )
                ), Category(
                    2,
                    context.resources.getStringArray(R.array.quest_mate),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_mate)[0],
                            context.resources.getStringArray(R.array.ans_mate1),
                            "Martin Gardner"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_mate)[1],
                            context.resources.getStringArray(R.array.ans_mate2),
                            "2 potencia infinito"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_mate)[2],
                            context.resources.getStringArray(R.array.ans_mate3),
                            "4000 veces la poblacion de la tierra"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_mate)[3],
                            context.resources.getStringArray(R.array.ans_mate4),
                            "que todos los numeros son la suma de dos primos"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_mate)[4],
                            context.resources.getStringArray(R.array.ans_mate5),
                            "4"
                        )
                    )
                ),
                Category(
                    3,
                    context.resources.getStringArray(R.array.quest_fisica),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_fisica)[0],
                            context.resources.getStringArray(R.array.ans_fisica1),
                            "region del universo a donde se dirige todo"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_fisica)[1],
                            context.resources.getStringArray(R.array.ans_fisica2),
                            "el neutrino"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_fisica)[2],
                            context.resources.getStringArray(R.array.ans_fisica3),
                            "todas son correctas"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_fisica)[3],
                            context.resources.getStringArray(R.array.ans_fisica4),
                            "El antineutron"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_fisica)[4],
                            context.resources.getStringArray(R.array.ans_fisica5),
                            "Meterte dentro de un coche"
                        )
                    )
                ),
                Category(
                    4,
                    context.resources.getStringArray(R.array.quest_comics),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_comics)[0],
                            context.resources.getStringArray(R.array.ans_comics1),
                            "6"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_comics)[1],
                            context.resources.getStringArray(R.array.ans_comics2),
                            "Jason Todd"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_comics)[2],
                            context.resources.getStringArray(R.array.ans_comics3),
                            "Darkside"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_comics)[3],
                            context.resources.getStringArray(R.array.ans_comics4),
                            "Infinity stones"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_comics)[4],
                            context.resources.getStringArray(R.array.ans_comics5),
                            "Krypton"
                        )
                    )
                ),
                Category(
                    5,
                    context.resources.getStringArray(R.array.quest_videojuegos),
                    questions = arrayOf(
                        Question(
                            context.resources.getStringArray(R.array.quest_videojuegos)[0],
                            context.resources.getStringArray(R.array.ans_videojuegos1),
                            "Final Fantasy"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_videojuegos)[1],
                            context.resources.getStringArray(R.array.ans_videojuegos2),
                            "Dark Souls"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_videojuegos)[2],
                            context.resources.getStringArray(R.array.ans_videojuegos3),
                            "Doom"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_videojuegos)[3],
                            context.resources.getStringArray(R.array.ans_videojuegos4),
                            "Ninguno"
                        ),
                        Question(
                            context.resources.getStringArray(R.array.quest_videojuegos)[4],
                            context.resources.getStringArray(R.array.ans_videojuegos5),
                            "Blue Shell"
                        )
                    )
                )
            )
        gameQuestion = getGameQuestions()
        currentListOfAnswer = getNewListOfCurrentAnswers()
    }
*/
    private fun setQuestions(list: MutableList<Category>): MutableList<Question> {

        val arrayOfQuestions: MutableList<Question> = mutableListOf()
        var auxList: MutableList<Question>
        var contOfIteration = 0
        var contAux = 0


        if(conf.categoriesNumber == 1){
            conf.questions_number = 5
        }

        while (contOfIteration < conf.questions_number) {

            if (contAux >= list.size) {
                contAux = 0
            }
            auxList = list[contAux].questions.toList() as MutableList<Question>
            auxList.shuffle()
            var cont = 0
            while(arrayOfQuestions.firstOrNull{ x -> x.question == auxList[cont].question} != null){
                cont++
            }
            arrayOfQuestions.add(auxList[cont])
            contOfIteration++
            contAux++
        }
        return arrayOfQuestions
    }

    fun getGameQuestions(): MutableList<Question> {
        val arrayOfQuestions: MutableList<Int> = mutableListOf()
        for ((indice, value) in conf.categories.withIndex()) {
            if (value) {
                arrayOfQuestions.add(indice)
            }
        }
        val finalQuest: MutableList<Category> = mutableListOf()
        for ((indice, value) in arrayOfQuestions.withIndex()) {
            finalQuest.add(conf.categoriesList.find { x -> x.id == value }!!)

        }
        finalQuest.shuffle()

        return setQuestions(finalQuest)
    }

    val numOfQuestion get() = conf.questions_number

    val numOfClues get() = conf.clues_number

    fun getCurrentQuestion() = gameQuestion[currentQuestion].question

    fun getCurrentQuestionObject() = gameQuestion[currentQuestion]

    fun getCorretAnswer() = gameQuestion[currentQuestion].answer

    fun getCurrentQuestionNum() = currentQuestion

    fun getNewListOfCurrentAnswers() : List<String>{
        var aux = gameQuestion[currentQuestion].arrayOfAnswer.toList() as MutableList<String>
        aux.shuffle()
        currentListOfAnswer =  aux

        return aux
    }

    fun setAnsweredAt(pos: Int, string:String){
        gameQuestion[getCurrentQuestionNum()].isCorrect = string == gameQuestion[getCurrentQuestionNum()].answer
        gameQuestion[getCurrentQuestionNum()].answered = string
        if(gameQuestion[getCurrentQuestionNum()].isCorrect) {
            player.numOfQuestionCorrect++
            player.score += pointsValue
        }
        gameQuestion[pos].state = true
        answeredCont++
    }

    fun setPlayerCheater(){
        player.cheater = true
    }

    fun nextQuestion() {
        currentQuestion = (currentQuestion + 1) % gameQuestion.size
        currentQuestionState = gameQuestion[currentQuestion].state
        currentQuestionClueUsed = false    }

    fun previousQuestion() {
        currentQuestion = (currentQuestion + gameQuestion.size - 1) % gameQuestion.size
        currentQuestionState = gameQuestion[currentQuestion].state
        currentQuestionClueUsed = false
    }




}
