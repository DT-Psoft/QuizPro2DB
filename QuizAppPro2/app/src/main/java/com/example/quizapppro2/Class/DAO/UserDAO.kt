package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.Entities.UserETY

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun AddUser(user : UserETY)
    @Delete
    fun deleteUser(vararg User: UserETY)
    @Query("DELETE FROM user where id_user = :id")
    fun deleteUserById(id:Int)

    @Query("SELECT*FROM user where user_name = :name")
    fun getUserByName(name: String): UserETY

    @Query("SELECT*FROM user where user_name = :name AND password = :password")
    fun getUserByLoggin(name: String, password: String): UserETY?

    @Query("SELECT*FROM user where is_logged = 1")
    fun getUserByIsLogged(): UserETY


}