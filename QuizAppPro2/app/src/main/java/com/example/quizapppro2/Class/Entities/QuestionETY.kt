package com.example.quizapppro2.Class.Entities

import androidx.room.*

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
){
    @Ignore
    var answered: String = ""
    @Ignore
    var state: Boolean = false
    @Ignore
    var isCorrect: Boolean = true
}