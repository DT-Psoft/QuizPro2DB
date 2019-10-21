package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.LastGame_AnswerETY

@Dao
interface LastGame_AnswerDAO {
    @Query("SELECT * FROM lastgame_answer ORDER BY id_lastgame_answer")
    fun getAll(): List<LastGame_AnswerETY>
}