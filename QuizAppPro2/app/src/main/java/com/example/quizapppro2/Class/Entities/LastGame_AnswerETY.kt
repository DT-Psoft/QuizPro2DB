package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame_answer", foreignKeys =
    [ForeignKey(
        entity = LastGameETY::class,
        parentColumns = ["id_lastgame"],
        childColumns = ["lastgame_id"], onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = AnswerETY::class,
            parentColumns = ["id_answer"],
            childColumns = ["answer_id"], onDelete = ForeignKey.CASCADE
        )],
            indices = [androidx.room.Index(value = ["lastgame_id"], unique = true)]
)

data class LastGame_AnswerETY (
    @field:ColumnInfo(name = "answer_id", index = true) var answer_id: Int,
    @field:ColumnInfo(name = "lastgame_id") var lastgame_question_id: Int
){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_lastgame_answer") var id_lastgame_answer: Int = 0
}