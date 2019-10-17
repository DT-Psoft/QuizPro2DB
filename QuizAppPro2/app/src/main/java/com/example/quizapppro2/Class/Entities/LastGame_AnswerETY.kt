package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame_answer", foreignKeys = arrayOf(
    ForeignKey(
        entity = UserETY::class,
        parentColumns = arrayOf("question_id"),
        childColumns = arrayOf("id_lastgame_question")
    ),
    ForeignKey(
        entity = UserETY::class,
        parentColumns = arrayOf("answer_id"),
        childColumns = arrayOf("id_answer")
    )
))

class LastGame_AnswerETY (
    @PrimaryKey @ColumnInfo(name = "id_lastgame_answer") var id_lastgame_question: Int,
    @field:ColumnInfo(name = "question_id") var question_id: Int,
    @field:ColumnInfo(name = "answered_id") var answered_id: Int,
    @field:ColumnInfo(name = "answer_by_user") var answer_by_user: Int = 0
)