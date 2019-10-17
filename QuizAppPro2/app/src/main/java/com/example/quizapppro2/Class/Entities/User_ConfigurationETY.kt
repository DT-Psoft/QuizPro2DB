package com.example.quizapppro2.Class.Entities

import androidx.room.*

@Entity(tableName = "user_configuration", foreignKeys = arrayOf(
    ForeignKey(
        entity = UserETY::class,
        parentColumns = arrayOf("id_user"),
        childColumns = arrayOf("user_id")
    )
))
data class User_ConfigurationETY (
    @PrimaryKey @ColumnInfo(name = "id_user_config") var id_user_config: Int,
    @field:ColumnInfo(name = "number_of_questions") var number_of_questions: Int = 5,
    @field:ColumnInfo(name = "number_of_categories") var number_of_categories: Int = 6,
    @field:ColumnInfo(name = "all_categories") var all_categories: Int = 1,
    @ColumnInfo(name = "categories_selected") var categories_selected: String = "111111",
    @field:ColumnInfo(name = "dificulty") var dificulty: Int = 0,
    @field:ColumnInfo(name = "clues_on") var clues_on: Int = 0,
    @field:ColumnInfo(name = "number_of_clues") var number_of_clues: Int = 1,
    @field:ColumnInfo(name = "user_id") var user_id: Int
)