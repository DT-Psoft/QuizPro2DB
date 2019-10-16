package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey @ColumnInfo(name = "id_user") var id_user: Int,
    @field:ColumnInfo(name = "user_name") var user_name: String,
    @field:ColumnInfo(name = "password") var number_of_categories: Int,
    @field:ColumnInfo(name = "is_logged") var all_categories: Int
)