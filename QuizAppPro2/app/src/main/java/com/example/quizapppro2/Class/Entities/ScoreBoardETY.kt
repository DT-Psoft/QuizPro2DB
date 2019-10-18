package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "scoreboard", foreignKeys =
[ForeignKey(
        entity = UserETY::class,
        parentColumns = arrayOf("id_user"),
        childColumns = arrayOf("user_id"), onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["user_id"], unique = true)
    ]
)

data class ScoreBoardETY (
    @PrimaryKey @ColumnInfo(name = "id_scoreboard") var id_scoreboard: Int,
    @field:ColumnInfo(name = "num_of_question") var num_of_question: Int,
    @field:ColumnInfo(name = "num_of_question_correct") var num_of_question_correct: Int = 0,
    @field:ColumnInfo(name = "cheater") var cheater: Int = 0,
    @field:ColumnInfo(name = "score") var score: Int = 0,
    @field:ColumnInfo(name = "user_id") var user_id: Int
)