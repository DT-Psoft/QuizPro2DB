package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.AnswerETY

@Dao
interface AnswerDAO {

    @Query("SELECT * FROM answer")
    fun getAll(): MutableList<AnswerETY>

    @Query("SELECT a.* FROM answer a JOIN question q ON a.question_id = q.id_question where a.question_id = :id")
    fun getAnswerByCategoryId(id: Int): MutableList<AnswerETY>
}