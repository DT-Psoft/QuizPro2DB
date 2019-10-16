package com.example.quizapppro2.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quizapppro2.Class.CategoryClass
import com.example.quizapppro2.Class.QuestionClass
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
    lateinit var gameQuestionClass : List<QuestionClass>
    lateinit var currentListOfAnswer : List<String>

    var score =  Score.score
    var player =  Score.score.currentPlayer
    var pointsValue =  conf.getPointsValue()

   /* init {
        conf.categoriesList =
            listOf(
                CategoryClass(
                    0,
                    context.resources.getStringArray(R.array.quest_cine),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_cine)[0],
                            context.resources.getStringArray(R.array.ans_cine1),
                            "22"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_cine)[1],
                            context.resources.getStringArray(R.array.ans_cine2),
                            "The Hateful Eight"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_cine)[2],
                            context.resources.getStringArray(R.array.ans_cine3),
                            "Jurassic Park"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_cine)[3],
                            context.resources.getStringArray(R.array.ans_cine4),
                            "Pixar"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_cine)[4],
                            context.resources.getStringArray(R.array.ans_cine5),
                            "Terminator"
                        )

                    )
                ),
                CategoryClass(
                    1, context.resources.getStringArray(R.array.quest_historia),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_historia)[0],
                            context.resources.getStringArray(R.array.ans_historia),
                            "Egipcios"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_historia)[1],
                            context.resources.getStringArray(R.array.ans_historia1),
                            "XIV"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_historia)[2],
                            context.resources.getStringArray(R.array.ans_historia3),
                            "1821"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_historia)[3],
                            context.resources.getStringArray(R.array.ans_historia4),
                            "Imperio Britanico"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_historia)[4],
                            context.resources.getStringArray(R.array.ans_historia5),
                            "1917"
                        )

                    )
                ), CategoryClass(
                    2,
                    context.resources.getStringArray(R.array.quest_mate),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_mate)[0],
                            context.resources.getStringArray(R.array.ans_mate1),
                            "Martin Gardner"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_mate)[1],
                            context.resources.getStringArray(R.array.ans_mate2),
                            "2 potencia infinito"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_mate)[2],
                            context.resources.getStringArray(R.array.ans_mate3),
                            "4000 veces la poblacion de la tierra"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_mate)[3],
                            context.resources.getStringArray(R.array.ans_mate4),
                            "que todos los numeros son la suma de dos primos"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_mate)[4],
                            context.resources.getStringArray(R.array.ans_mate5),
                            "4"
                        )
                    )
                ),
                CategoryClass(
                    3,
                    context.resources.getStringArray(R.array.quest_fisica),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_fisica)[0],
                            context.resources.getStringArray(R.array.ans_fisica1),
                            "region del universo a donde se dirige todo"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_fisica)[1],
                            context.resources.getStringArray(R.array.ans_fisica2),
                            "el neutrino"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_fisica)[2],
                            context.resources.getStringArray(R.array.ans_fisica3),
                            "todas son correctas"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_fisica)[3],
                            context.resources.getStringArray(R.array.ans_fisica4),
                            "El antineutron"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_fisica)[4],
                            context.resources.getStringArray(R.array.ans_fisica5),
                            "Meterte dentro de un coche"
                        )
                    )
                ),
                CategoryClass(
                    4,
                    context.resources.getStringArray(R.array.quest_comics),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_comics)[0],
                            context.resources.getStringArray(R.array.ans_comics1),
                            "6"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_comics)[1],
                            context.resources.getStringArray(R.array.ans_comics2),
                            "Jason Todd"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_comics)[2],
                            context.resources.getStringArray(R.array.ans_comics3),
                            "Darkside"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_comics)[3],
                            context.resources.getStringArray(R.array.ans_comics4),
                            "Infinity stones"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_comics)[4],
                            context.resources.getStringArray(R.array.ans_comics5),
                            "Krypton"
                        )
                    )
                ),
                CategoryClass(
                    5,
                    context.resources.getStringArray(R.array.quest_videojuegos),
                    questionClasses = arrayOf(
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_videojuegos)[0],
                            context.resources.getStringArray(R.array.ans_videojuegos1),
                            "Final Fantasy"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_videojuegos)[1],
                            context.resources.getStringArray(R.array.ans_videojuegos2),
                            "Dark Souls"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_videojuegos)[2],
                            context.resources.getStringArray(R.array.ans_videojuegos3),
                            "Doom"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_videojuegos)[3],
                            context.resources.getStringArray(R.array.ans_videojuegos4),
                            "Ninguno"
                        ),
                        QuestionClass(
                            context.resources.getStringArray(R.array.quest_videojuegos)[4],
                            context.resources.getStringArray(R.array.ans_videojuegos5),
                            "Blue Shell"
                        )
                    )
                )
            )
        gameQuestionClass = getGameQuestions()
        currentListOfAnswer = getNewListOfCurrentAnswers()
    }
*/
    private fun setQuestions(list: MutableList<CategoryClass>): MutableList<QuestionClass> {

        val arrayOfQuestionClasses: MutableList<QuestionClass> = mutableListOf()
        var auxList: MutableList<QuestionClass>
        var contOfIteration = 0
        var contAux = 0


        if(conf.categoriesNumber == 1){
            conf.questions_number = 5
        }

        while (contOfIteration < conf.questions_number) {

            if (contAux >= list.size) {
                contAux = 0
            }
            auxList = list[contAux].questionClasses.toList() as MutableList<QuestionClass>
            auxList.shuffle()
            var cont = 0
            while(arrayOfQuestionClasses.firstOrNull{ x -> x.question == auxList[cont].question} != null){
                cont++
            }
            arrayOfQuestionClasses.add(auxList[cont])
            contOfIteration++
            contAux++
        }
        return arrayOfQuestionClasses
    }

    fun getGameQuestions(): MutableList<QuestionClass> {
        val arrayOfQuestions: MutableList<Int> = mutableListOf()
        for ((indice, value) in conf.categories.withIndex()) {
            if (value) {
                arrayOfQuestions.add(indice)
            }
        }
        val finalQuest: MutableList<CategoryClass> = mutableListOf()
        for ((indice, value) in arrayOfQuestions.withIndex()) {
            finalQuest.add(conf.categoriesList.find { x -> x.id == value }!!)

        }
        finalQuest.shuffle()

        return setQuestions(finalQuest)
    }

    val numOfQuestion get() = conf.questions_number

    val numOfClues get() = conf.clues_number

    fun getCurrentQuestion() = gameQuestionClass[currentQuestion].question

    fun getCurrentQuestionObject() = gameQuestionClass[currentQuestion]

    fun getCorretAnswer() = gameQuestionClass[currentQuestion].answer

    fun getCurrentQuestionNum() = currentQuestion

    fun getNewListOfCurrentAnswers() : List<String>{
        var aux = gameQuestionClass[currentQuestion].arrayOfAnswer.toList() as MutableList<String>
        aux.shuffle()
        currentListOfAnswer =  aux

        return aux
    }

    fun setAnsweredAt(pos: Int, string:String){
        gameQuestionClass[getCurrentQuestionNum()].isCorrect = string == gameQuestionClass[getCurrentQuestionNum()].answer
        gameQuestionClass[getCurrentQuestionNum()].answered = string
        if(gameQuestionClass[getCurrentQuestionNum()].isCorrect) {
            player.numOfQuestionCorrect++
            player.score += pointsValue
        }
        gameQuestionClass[pos].state = true
        answeredCont++
    }

    fun setPlayerCheater(){
        player.cheater = true
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
