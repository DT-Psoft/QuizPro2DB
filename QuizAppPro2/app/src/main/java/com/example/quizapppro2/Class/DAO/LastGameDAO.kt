package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.Entities.LastGameETY

@Dao
interface LastGameDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastGame(lastgame:LastGameETY): Long

    @Query("SELECT id_lastgame FROM lastgame WHERE user_id = :id and is_active = 1")
    fun getIdLastgameIdByUserIdAndIsActive(id:Int): Int
    @Query("SELECT id_lastgame FROM lastgame WHERE user_id = :id and is_active = 1")
    fun getIdLastgameIdByUserIdAndIsActiveNonNull(id:Int): Int?

    @Query("SELECT * FROM lastgame WHERE user_id = :id and is_active = 1")
    fun getIdLastgameByUserIdAndIsActive(id:Int): LastGameETY

    @Query("SELECT * FROM lastgame WHERE user_id = :id and is_active = 1")
    fun getIdLastgameByUserIdAndIsActiveNonNull(id:Int): LastGameETY?

    @Query("SELECT * FROM lastgame WHERE user_id = :id")
    fun getLastgameByUserIdNonNull(id:Int): LastGameETY?

    @Query("SELECT * FROM lastgame WHERE user_id = :id")
    fun getLastgameByUserId(id:Int): LastGameETY

    @Query("SELECT * FROM lastgame WHERE id_lastgame= :id")
    fun getLastgameById(id:Int): LastGameETY

    @Update
    fun updateLastGame(lastgame:LastGameETY)

    @Delete
    fun deleteLastGame(lastgame: LastGameETY)
}