package com.example.quizapppro2.Class.DAO


import androidx.room.Dao
import androidx.room.Query
import com.example.quizapppro2.Class.Entities.*




@Dao
interface CategoryDAO {
    @Query("SELECT * FROM category ORDER BY id_category")
    fun getAll(): List<CategoryETY>
}