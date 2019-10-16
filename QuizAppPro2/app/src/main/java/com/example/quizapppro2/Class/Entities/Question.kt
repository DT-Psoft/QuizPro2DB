package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "question")
data class Question (
    @PrimaryKey @ColumnInfo(name = "id_question") var id_question: Int,
    @field:ColumnInfo(name = "question_text") var question_text: String,
    @field:ColumnInfo(name = "correct_answer_id") var correct_answer_id: Int,
    @field:ColumnInfo(name = "category_id") var category_id: Int //,
   // @ForeignKey @ColumnInfo(name = "category_id") var number_of_questions: Int
)