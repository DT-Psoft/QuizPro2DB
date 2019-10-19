package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY



@Dao
interface User_ConfigurationDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun AddConfiguration(config : User_ConfigurationETY)

    @Delete
    fun deleteConfiguration(vararg config: User_ConfigurationETY)
    @Query("DELETE FROM user_configuration where id_user_config= :id")
    fun deleteConfigurationById(id:Int)
    @Update
    fun updateConfiguration(vararg config : User_ConfigurationETY)

    @Query("SELECT * FROM user_configuration WHERE user_id = :id")
    fun getConfigurationByUserId(id: Int) : User_ConfigurationETY

}