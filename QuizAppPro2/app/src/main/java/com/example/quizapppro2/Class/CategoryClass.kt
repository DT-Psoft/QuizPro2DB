package com.example.quizapppro2.Class

import com.example.quizapppro2.Class.QuestionClass

data class CategoryClass(val id: Int, val questions_string:Array<String>, val questionClasses: Array<QuestionClass>, var isEnabled : Boolean = true)
