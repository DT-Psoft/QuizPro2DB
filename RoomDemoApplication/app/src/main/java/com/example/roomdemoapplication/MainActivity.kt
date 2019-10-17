package com.example.roomdemoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.example.roomdemoapplication.db.AppDatabase
import com.example.roomdemoapplication.db.CourseLevel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        val levels = db.getCourseLevelDao().getAll()

        val cl: CourseLevel = db.getCourseLevelDao().getCourseLevelById(4)
        cl.requiredCredits = 277
        db.getCourseLevelDao().updateCourseLevel(cl)

        val professors = db.professorDao().getByText("le")

        val p = db.professorDao().getById(1262)
        p.categoryId = 5
        db.professorDao().updateProfessor(p)

        val reportData = db.reportsDao().getCourseLevelReportData()
    }
}
