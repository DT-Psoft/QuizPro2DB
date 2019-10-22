package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.ScoreBoardETY

@Dao
interface ScoreBoardDAO {
    @Query("SELECT * FROM scoreboard")
    fun getAll(): MutableList<ScoreBoardETY>

    @Query("SELECT * FROM scoreboard ORDER BY score DESC")
    fun getAllOrderByScore(): MutableList<ScoreBoardETY>
    @Query("SELECT * FROM scoreboard ORDER BY score DESC LIMIT 6")
    fun getAllOrderByScoreLimit6(): MutableList<ScoreBoardETY>

    @Query("SELECT*FROM scoreboard WHERE id_scoreboard = :id ")
    fun getScoreboardById(id:Int):ScoreBoardETY

    @Insert
    fun insertScoreboard(scoreBoard: ScoreBoardETY)
    @Delete
    fun deleteScoreboard(scoreBoard: ScoreBoardETY)
    @Update
    fun updatetScoreboard(scoreBoard: ScoreBoardETY)
}