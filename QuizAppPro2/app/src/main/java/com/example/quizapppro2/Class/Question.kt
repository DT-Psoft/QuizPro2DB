package com.example.quizapppro2.Class

data class Question(
    var question: String,
    val arrayOfAnswer: Array<String>,
    val answer: String,
    var answered: String = "",
    var state: Boolean = false,
    var isCorrect: Boolean = true
)