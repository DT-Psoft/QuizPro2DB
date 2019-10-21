package com.example.quizapppro2.Class.Entities

import androidx.room.*

@Entity(tableName = "category")
data class CategoryETY (
    @PrimaryKey @ColumnInfo(name = "id_category", index = true) var id_category: Int,
    @field:ColumnInfo(name = "category_name") var category_name: String
){
    @Ignore
    var isEnabled : Boolean = true
}