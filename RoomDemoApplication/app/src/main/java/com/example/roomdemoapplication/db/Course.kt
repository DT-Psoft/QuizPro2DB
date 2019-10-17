package com.example.roomdemoapplication.db

import androidx.room.*

@Entity(
    tableName = "courses",
    foreignKeys = [ForeignKey(
        entity = CourseLevel::class,
        parentColumns = ["id"],
        childColumns = ["level_id"]
    )],
    indices = [
        Index(value = ["description"], unique = true),
        Index(value = ["id", "level_id"], unique = true)
    ]
)
data class Course(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "level_id", index = true) val levelId: Int,
    @ColumnInfo(name = "active", typeAffinity = ColumnInfo.INTEGER) val active: Boolean,
    @ColumnInfo(name = "description") val description: String
)
