package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "question",
        foreignKeys = [ForeignKey(
        entity = CategoryETY::class,
        parentColumns = arrayOf("question_id"),
        childColumns = arrayOf("category_id"), onDelete = ForeignKey.CASCADE
)],
    indices = [
        Index(value = ["category_id"], unique = true)
    ]

)
data class QuestionETY (
    @PrimaryKey @ColumnInfo(name = "question_id", index = true) var question_id: Int,
    @field:ColumnInfo(name = "question_text") var question_text: String,
    @field:ColumnInfo(name = "correct_answer_id") var correct_answer_id: Int,
    @field:ColumnInfo(name = "category_id") var category_id: Int
)