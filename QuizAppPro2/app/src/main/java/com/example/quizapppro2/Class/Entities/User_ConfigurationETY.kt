package com.example.quizapppro2.Class.Entities

import androidx.room.*

@Entity(tableName = "user_configuration", foreignKeys = [ForeignKey(
        entity = UserETY::class,
        parentColumns = ["id_user"],
        childColumns = ["user_id"], onDelete = ForeignKey.CASCADE
    )],
        indices = [Index(value = ["user_id"], unique = true)]
)
data class User_ConfigurationETY(@ColumnInfo(name="user_id") var user_id: Int) {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_user_config", index = true) var id_user_config: Int = 0
    @field:ColumnInfo(name = "number_of_questions") var number_of_questions: Int = 5
    @field:ColumnInfo(name = "number_of_categories") var number_of_categories: Int = 6
    @field:ColumnInfo(name = "all_categories") var all_categories: Int = 1
    @field:ColumnInfo(name = "categories_selected") var categories_selected: String = "111111"
    @field:ColumnInfo(name = "dificulty") var dificulty: Int = 0
    @field:ColumnInfo(name = "clues_on") var clues_on: Int = 0
    @field:ColumnInfo(name = "number_of_clues") var number_of_clues: Int = 1
}
