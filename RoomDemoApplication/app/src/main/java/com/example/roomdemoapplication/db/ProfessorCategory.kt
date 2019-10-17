package com.example.roomdemoapplication.db

import androidx.room.*

@Entity(
    tableName = "professor_categories",
    indices = [Index(value = ["description"], unique = true)]
)
data class ProfessorCategory(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "payment") val payment: Int
)
