package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.Entities.LastGameETY
import com.example.quizapppro2.Class.Entities.LastGame_QuestionETY

@Dao
interface LastGame_QuestionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastGameQuestion(lastgame:LastGame_QuestionETY): Long


    @Query("SELECT q.* FROM lastgame_question q JOIN lastgame l ON q.lastgame_id = l.id_lastgame where q.lastgame_id = :id")
    fun getLastGameQuestionsByLastGameId(id: Int): MutableList<LastGame_QuestionETY>

    @Delete
    fun deleteLastGame(lastgame: LastGame_QuestionETY)

    @Query("DELETE FROM lastgame_question WHERE lastgame_id = :idLastgame")
    fun deleteLastGameQuestionByIdLastGame(idLastgame:Int)

}