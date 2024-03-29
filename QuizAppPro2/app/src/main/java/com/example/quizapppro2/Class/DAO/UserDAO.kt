package com.example.quizapppro2.Class.DAO

import androidx.room.*
import com.example.quizapppro2.Class.AppDatabase
import com.example.quizapppro2.Class.Entities.UserETY
import com.example.quizapppro2.Class.DAO.User_ConfigurationDAO
import com.example.quizapppro2.Class.Entities.User_ConfigurationETY
import com.example.quizapppro2.Views.Configuration

@Dao
interface UserDAO: User_ConfigurationDAO {

    @Transaction
    fun InsertUserWithConfig(user:UserETY): Boolean{
        var id = AddUser(user)
        if(id == -1L) {
            return false
        }
        else{
            AddConfiguration(User_ConfigurationETY(id.toInt()))
        }
        return true
    }
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun AddUser(user : UserETY): Long

    @Update
    fun UpdateUser(user: UserETY)

    @Delete
    fun deleteUser(vararg User: UserETY)

    @Query("DELETE FROM user where id_user = :id")
    fun deleteUserById(id:Int)

    @Query("SELECT * FROM user WHERE user_name = :name")
    fun getUserByName(name: String): UserETY?
    @Query("SELECT * FROM user WHERE id_user= :id")
    fun getUserById(id:Int): UserETY

    @Query("SELECT*FROM user where user.is_logged= 1")
    fun getUserByIsLogged(): UserETY?

    @Query("SELECT*FROM user where user.is_logged= 1")
    fun getUserByIsLoggedNullable(): UserETY


    @Query("SELECT*FROM user")
    fun getAllUsers(): Array<UserETY>
}