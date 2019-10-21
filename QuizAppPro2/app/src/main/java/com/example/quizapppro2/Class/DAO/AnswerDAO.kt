package com.example.quizapppro2.Class.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizapppro2.Class.Entities.AnswerETY

@Dao
interface AnswerDAO {

    @Query("SELECT * FROM answer")
    fun getAll(): MutableList<AnswerETY>

    @Query("SELECT a.* FROM answer a JOIN question q ON a.question_id = q.id_question where a.question_id = :id")
    fun getAnswerByQuestionId(id: Int): MutableList<AnswerETY>

    @Query("SELECT a.answer_text FROM answer a JOIN question q ON a.question_id = q.id_question where a.question_id = :id")
    fun getAnswerTextByQuestionId(id: Int): MutableList<String>


    @Query("SELECT a.* FROM answer a JOIN question q ON a.question_id = q.id_question where a.question_id = :idQuestion AND a.is_correct = 1")
    fun getCorrectAnswer(idQuestion: Int): AnswerETY
}