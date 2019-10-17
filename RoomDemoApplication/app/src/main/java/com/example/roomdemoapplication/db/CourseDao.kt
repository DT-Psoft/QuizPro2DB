package com.example.roomdemoapplication.db

import androidx.room.*

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses ORDER BY description")
    fun getAll(): List<Course>

    @Query("SELECT * FROM courses WHERE active = 1 ORDER BY description")
    fun getAllActive(): List<Course>

    @Query("SELECT * FROM courses WHERE level_id = :levelId ORDER BY description")
    fun getByLevel(levelId: Int): List<Course>

    @Query("SELECT * FROM courses WHERE level_id IN (:levelIds) ORDER BY description")
    fun getByLevels(levelIds: IntArray): List<Course>
}
