package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame_answer", foreignKeys =
    [ForeignKey(
        entity = LastGame_QuestionETY::class,
        parentColumns = ["id_lastgame_question"],
        childColumns = ["lastgame_question_id"], onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = AnswerETY::class,
            parentColumns = ["id_answer"],
            childColumns = ["answer_id"], onDelete = ForeignKey.CASCADE
        )]
)

class LastGame_AnswerETY (
    @PrimaryKey @ColumnInfo(name = "id_lastgame_answer") var id_lastgame_answer: Int,
    @field:ColumnInfo(name = "lastgame_question_id", index = true) var lastgame_question_id: Int,
    @field:ColumnInfo(name = "answer_id", index = true) var answer_id: Int,
    @field:ColumnInfo(name = "answer_by_user") var answer_by_user: Int = 0
)