package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "scoreboard", foreignKeys =
[ForeignKey(
        entity = UserETY::class,
        parentColumns = ["id_user"],
        childColumns = ["user_id"], onDelete = ForeignKey.CASCADE
    )]
)

data class ScoreBoardETY (
    @field:ColumnInfo(name = "num_of_question") var num_of_question: Int,
    @field:ColumnInfo(name = "user_id", index = true) var user_id: Int
){
    @field:ColumnInfo(name = "num_of_question_correct") var num_of_question_correct: Int = 0
    @field:ColumnInfo(name = "cheater") var cheater: Int = 0
    @field:ColumnInfo(name = "score") var score: Int = 0
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id_scoreboard") var id_scoreboard: Int = 0
}