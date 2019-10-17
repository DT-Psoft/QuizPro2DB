package com.example.quizapppro2.Class.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryETY (
    @PrimaryKey @ColumnInfo(name = "id_category") var id_category: Int,
    @field:ColumnInfo(name = "category_name") var category_name: String
)