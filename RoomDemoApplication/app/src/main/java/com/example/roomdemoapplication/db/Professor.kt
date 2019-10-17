package com.example.roomdemoapplication.db

import androidx.room.*

@Entity(
    tableName = "professors",
    foreignKeys = [ForeignKey(
        entity = ProfessorCategory::class,
        parentColumns = ["id"],
        childColumns = ["category_id"]
    )]
)
data class Professor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "fname") val firstName: String,
    @ColumnInfo(name = "lname") val lastName: String,
    @ColumnInfo(name = "category_id", index = true) var categoryId: Int
)
