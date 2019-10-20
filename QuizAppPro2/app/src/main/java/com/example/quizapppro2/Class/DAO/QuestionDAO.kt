package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.QuestionETY


@Dao
interface QuestionDAO {
    @Query("SELECT * FROM question WHERE id_question = :id")
    fun getConfigurationByUserId(id: Int) : QuestionETY
}