package com.example.roomdemoapplication.db

import androidx.room.*

@Dao
interface ProfessorDao {

    @Query("SELECT * FROM professors ORDER BY lname, fname")
    fun getAll(): List<Professor>

    @Query("SELECT * FROM professors WHERE id = :id ORDER BY lname, fname")
    fun getById(id: Int): Professor

    @Query("SELECT * FROM professors WHERE fname LIKE '%'||:text||'%' OR lname LIKE '%'||:text||'%' ORDER BY lname, fname")
    fun getByText(text: String): List<Professor>

    @Query("SELECT * FROM professors WHERE category_id = :categoryId ORDER BY lname, fname")
    fun getByCategory(categoryId: Int): List<Professor>

    @Query("SELECT * FROM professors WHERE category_id IN (:categoryIds) ORDER BY lname, fname")
    fun getByCategories(categoryIds: IntArray): List<Professor>

    @Update
    fun updateProfessor(professor: Professor)
}
