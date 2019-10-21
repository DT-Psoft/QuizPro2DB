package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.Entities.LastGame_AnswerETY

@Dao
interface LastGame_AnswerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastGameAnswer(lastgame:LastGame_AnswerETY)

}