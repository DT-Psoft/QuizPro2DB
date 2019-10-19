package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "lastgame",foreignKeys =
    [ForeignKey(
        entity = UserETY::class,
        parentColumns = ["id_user"],
        childColumns = ["user_id"], onDelete = ForeignKey.CASCADE
            )]
)

data class LastGameETY (
    @PrimaryKey @ColumnInfo(name = "id_lastgame", index = true) var id_lastgame: Int,
    @field:ColumnInfo(name = "dificulty") var dificulty: Int,
    @field:ColumnInfo(name = "number_of_questions") var number_of_questions: Int,
    @field:ColumnInfo(name = "current_question") var current_question: Int,
    @field:ColumnInfo(name = "clues_on") var clues_on: Int,
    @field:ColumnInfo(name = "number_of_clues") var number_of_clues: Int,
    @field:ColumnInfo(name = "clues_left") var clues_left: Int,
    @field:ColumnInfo(name = "user_id", index=true) var user_id: Int
)