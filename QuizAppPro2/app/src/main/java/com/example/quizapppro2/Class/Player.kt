package com.example.quizapppro2.Class

import com.example.quizapppro2.Views.Configuration

data class Player(
    var initials: String = "---",
    var score: Int = 0,
    var numOfQuestionCorrect: Int = 0,
    var numOfQuestion:Int = Configuration.conf.questions_number,
    var cheater: Boolean = false){

    override fun toString(): String {
        return "$initials - Puntuación: $score Correctas: $numOfQuestionCorrect/$numOfQuestion"
    }
}
