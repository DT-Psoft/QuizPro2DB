package com.example.roomdemoapplication.db

import androidx.room.*

@Dao
interface ProfessorCategoryDao {
    @Query("SELECT * FROM professor_categories ORDER BY description")
    fun getAll(): List<ProfessorCategory>
}
