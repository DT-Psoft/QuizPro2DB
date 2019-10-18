package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserETY (
    @PrimaryKey @ColumnInfo(name = "id_user", index = true) var id_user: Int,
    @field:ColumnInfo(name = "user_name") var user_name: String,
    //@field:ColumnInfo(name = "password") var number_of_categories: Int,
    @field:ColumnInfo(name = "is_logged") var all_categories: Int = 0,
    @field:ColumnInfo(name = "image_user") var image_user: Int

)