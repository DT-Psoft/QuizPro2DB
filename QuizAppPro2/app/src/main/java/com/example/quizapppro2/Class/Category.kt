package com.example.quizapppro2.Class

data class Category(val id: Int, val questions_string:Array<String>, val questions: Array<Question>, var isEnabled : Boolean = true)
