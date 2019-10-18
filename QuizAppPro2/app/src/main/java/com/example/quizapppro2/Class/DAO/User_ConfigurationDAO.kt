package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY



@Dao
interface User_ConfigurationDAO{
    @Insert
    fun AddConfiguration(config : User_ConfigurationETY)

    @Insert
    fun updateConfiguration(vararg config : User_ConfigurationETY)

    @Query("SELECT * FROM user_configuration WHERE user_id = :id")
    fun getConfigurationByUserId(id: Int) : User_ConfigurationETY

}