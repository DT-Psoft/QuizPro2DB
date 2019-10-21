package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.AnswerETY
import com.example.quizapppro2.Class.Entities.CategoryETY
import com.example.quizapppro2.Class.Entities.QuestionETY


@Dao
interface QuestionDAO {

    @Query("SELECT * FROM question")
    fun getAll(): MutableList<QuestionETY>

    @Query("SELECT q.* FROM question q JOIN category c ON q.category_id = c.id_category where q.category_id = :id")
    fun getQuestionsByCategoryId(id: Int): MutableList<QuestionETY>

    @Query("SELECT * FROM question WHERE id_question = :id")
    fun getQuestionById(id:Int) : QuestionETY
}