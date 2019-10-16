package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_configuration")
data class User_Configuration (
    @PrimaryKey @ColumnInfo(name = "id_user_config") var id_user_config: Int,
    @field:ColumnInfo(name = "number_of_questions") var number_of_questions: Int,
    @field:ColumnInfo(name = "number_of_categories") var number_of_categories: Int,
    @field:ColumnInfo(name = "all_categories") var all_categories: Int,
    @ColumnInfo(name = "categories_selected") var categories_selected: String,
    @field:ColumnInfo(name = "dificulty") var dificulty: Int,
    @field:ColumnInfo(name = "clues_on") var clues_on: Int,
    @field:ColumnInfo(name = "number_of_clues") var number_of_clues: Int,
    @field:ColumnInfo(name = "user_id") var user_id: Int
)