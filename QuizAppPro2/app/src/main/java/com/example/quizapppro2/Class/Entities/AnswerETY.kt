package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "answer")
data class AnswerETY (
    @PrimaryKey @ColumnInfo(name = "id_answer") var id_answer: Int,
    @field:ColumnInfo(name = "answer_text") var answer_text: String,
    @field:ColumnInfo(name = "question_id") var question_id: String
)