package com.example.roomdemoapplication.db

import androidx.room.*

@Entity(tableName = "course_levels", indices = [Index(value = ["description"], unique = true)])
data class CourseLevel(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "payment_pct") val paymentPercentage: Int,
    @ColumnInfo(name = "required_credits") var requiredCredits: Int
)
