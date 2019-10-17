package com.example.roomdemoapplication.db

import androidx.room.*

@Dao
interface ReportsDao {
    data class CourseLevelReport(
        val levelId: Int,
        val levelDescription: String,
        val coursesQty: Int
    )

    @Query(
        "SELECT cl.id AS levelId, cl.description AS levelDescription, COUNT(c.id) AS coursesQty" +
                " FROM course_levels cl" +
                " INNER JOIN courses c ON (cl.id = c.level_id)" +
                " WHERE active = 1" +
                " GROUP BY cl.id, cl.description"
    )
    fun getCourseLevelReportData(): List<CourseLevelReport>
}
