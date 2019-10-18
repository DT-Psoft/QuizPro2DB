package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame_question",foreignKeys =
    [ForeignKey(
        entity = QuestionETY::class,
        parentColumns = arrayOf("question_id"),
        childColumns = arrayOf("id_question"), onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["id_question"], unique = true)
    ]
)
data class LastGame_QuestionETY (
    @PrimaryKey @ColumnInfo(name = "id_lastgame_question", index = true) var id_lastgame_question: Int,
    @field:ColumnInfo(name = "id_question") var id_question: Int,
    @field:ColumnInfo(name = "answered") var answered: Int = 0,
    @field:ColumnInfo(name = "is_correct") var is_correct: Int = 0
)