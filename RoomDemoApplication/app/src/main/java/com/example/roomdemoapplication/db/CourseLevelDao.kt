package com.example.roomdemoapplication.db

import androidx.room.*

@Dao
interface CourseLevelDao {

    @Query("SELECT * FROM course_levels WHERE id = :id")
    fun getCourseLevelById(id: Int): CourseLevel

    @Query("SELECT * FROM course_levels ORDER BY description")
    fun getAll(): List<CourseLevel>

    @Update
    fun updateCourseLevel(level: CourseLevel)

    @Update
    fun updateCourseLevel(level1: CourseLevel, level2: CourseLevel)

    @Update
    fun updateCourseLevel(levels: List<CourseLevel>)

    @Update
    fun updateCourseLevel(levels: Array<CourseLevel>)

    @Insert
    fun insertCourseLevel(level: CourseLevel)

    @Insert
    fun insertCourseLevel(level1: CourseLevel, level2: CourseLevel)

    @Insert
    fun insertCourseLevel(levels: List<CourseLevel>)

    @Insert
    fun insertCourseLevel(levels: Array<CourseLevel>)

    @Delete
    fun deleteCourseLevel(level: CourseLevel)

    @Delete
    fun deleteCourseLevel(level1: CourseLevel, level2: CourseLevel)

    @Delete
    fun deleteCourseLevel(levels: List<CourseLevel>)

    @Delete
    fun deleteCourseLevel(levels: Array<CourseLevel>)

    @Query("DELETE FROM course_levels")
    fun deleteAllCourseLevels()

//    @Insert
//    fun insertLevel(level: CourseLevel)
//
//    @Insert
//    fun insertLevels(levels: List<CourseLevel>)
//
//    @Insert
//    fun insertLevels(vararg levels: CourseLevel)
//
//    @Insert
//    fun insertLevels(level1: CourseLevel, level2: CourseLevel)
//
//    @Update
//    fun updateLevel(level: CourseLevel)
//
//    @Update
//    fun updateLevels(levels: List<CourseLevel>)
//
//    @Update
//    fun updateLevels(vararg levels: CourseLevel)
//
//    @Update
//    fun updateLevels(level1: CourseLevel, level2: CourseLevel)
//
//    @Delete
//    fun deleteLevel(level: CourseLevel)
//
//    @Delete
//    fun deleteLevels(levels: List<CourseLevel>)
//
//    @Delete
//    fun deleteLevels(vararg levels: CourseLevel)
//
//    @Delete
//    fun deleteLevels(level1: CourseLevel, level2: CourseLevel)
//
//    @Query("DELETE FROM course_levels WHERE id = :levelId")
//    fun deleteLevel(levelId: Int)
}
