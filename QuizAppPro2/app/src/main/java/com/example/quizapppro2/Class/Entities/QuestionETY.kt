package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "question",
        foreignKeys = [ForeignKey(
        entity = CategoryETY::class,
        parentColumns = ["id_category"],
        childColumns = ["category_id"], onDelete = ForeignKey.CASCADE
)]
)
data class QuestionETY (
    @PrimaryKey @ColumnInfo(name = "id_question", index = true) var question_id: Int,
    @field:ColumnInfo(name = "question_text") var question_text: String,
    @field:ColumnInfo(name = "category_id", index = true) var category_id: Int
)