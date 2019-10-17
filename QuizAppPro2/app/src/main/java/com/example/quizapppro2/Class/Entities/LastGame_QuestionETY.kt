package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame_question")
data class LastGame_QuestionETY (
    @PrimaryKey @ColumnInfo(name = "id_lastgame_question") var id_lastgame_question: Int,
    @field:ColumnInfo(name = "question_id") var question_id: Int,
    @field:ColumnInfo(name = "answered") var answered: Int,
    @field:ColumnInfo(name = "is_correct") var is_correct: Int
)