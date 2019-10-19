package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(tableName = "answer", foreignKeys =
    [ForeignKey(
        entity = QuestionETY::class,
        parentColumns = ["id_question"],
        childColumns = ["question_id"], onDelete = ForeignKey.CASCADE
    )]

)

data class AnswerETY (
    @PrimaryKey @ColumnInfo(name = "id_answer", index = true) var id_answer: Int,
    @field:ColumnInfo(name = "answer_text") var answer_text: String,
    @field:ColumnInfo(name = "is_correct") var is_correct: Int,
    @field:ColumnInfo(name = "question_id", index = true) var question_id: Int
)