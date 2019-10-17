package com.example.roomdemoapplication.db

import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        CourseLevel::class, Course::class,
        ProfessorCategory::class, Professor::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCourseLevelDao(): CourseLevelDao

    abstract fun courseDao(): CourseDao

    abstract fun professorCategoryDao(): ProfessorCategoryDao

    abstract fun professorDao(): ProfessorDao

    abstract fun reportsDao(): ReportsDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "faculty.db"
                )
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            initializeData(db)
                        }
                    })
                    .build()
            }

            return INSTANCE as AppDatabase
        }

        fun initializeData(db: SupportSQLiteDatabase) {
            //db.beginTransaction();

            db.execSQL("INSERT INTO course_levels (id, description, payment_pct, required_credits) VALUES (0, 'Licenciatura', 100, 120)")
            db.execSQL("INSERT INTO course_levels (id, description, payment_pct, required_credits) VALUES (1, 'Maestría', 150, 70)")
            db.execSQL("INSERT INTO course_levels (id, description, payment_pct, required_credits) VALUES (2, 'Doctorado', 175, 45)")
            db.execSQL("INSERT INTO course_levels (id, description, payment_pct, required_credits) VALUES (3, 'Diplomado', 100, 25)")
            db.execSQL("INSERT INTO course_levels (id, description, payment_pct, required_credits) VALUES (4, 'Especialidad', 125, 40)")

            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (0, 0, 1, 'Ingeniería Mecatrónica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (1, 0, 1, 'Ingeniería Física')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (2, 0, 0, 'Ingeniería Química')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (3, 0, 1, 'Ingeniería Electrónica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (4, 0, 1, 'Ingeniería Mecánica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (5, 0, 1, 'Ingeniería Civil')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (6, 0, 0, 'Ingeniería Ambiental')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (7, 0, 0, 'Ingeniería en Sistemas Computacionales')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (8, 0, 1, 'Ingeniería en Desarrollo de Software')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (9, 1, 0, 'Maestría en Ingeniería Ambiental')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (10, 1, 0, 'Maestría en Ingeniería de Software')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (11, 1, 1, 'Maestría en Ciencias en Electrónica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (12, 1, 1, 'Maestría en Ciencias en Sistemas Computacionales')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (13, 2, 1, 'Doctorado en Ingeniería en Energías Renovables')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (14, 2, 1, 'Doctorado en Ingeniería Mecatrónica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (15, 2, 0, 'Doctorado en Ciencias en Electrónica')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (16, 3, 0, 'Diplomado en Enseñanza de las Matemáticas')")
            db.execSQL("INSERT INTO courses (id, level_id, active, description) VALUES (17, 4, 1, 'Especialidad en Estadística')")

            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (0, 'Por horas A', 70)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (1, 'Por horas B', 80)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (2, 'De carrera A', 80)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (3, 'De carrera B', 90)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (4, 'De carrera C', 100)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (5, 'De carrera D', 120)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (6, 'Investigador A', 100)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (7, 'Investigador B', 140)")
            db.execSQL("INSERT INTO professor_categories (id, description, payment) VALUES (8, 'Investigador C', 180)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1262, 'Ramón', 'Suárez', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8245, 'Pedro', 'Palma', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1941, 'Alejandro', 'Lemus', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2835, 'Alexia', 'Morales', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (5625, 'Rodrigo', 'Burgos', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9156, 'Arturo', 'Cambranis', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9831, 'Roxana', 'Rodríguez', 3)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9724, 'Laura', 'Teherán', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1724, 'Mateo', 'Canché', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2895, 'Javier', 'Meléndez', 1)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2674, 'Israel', 'Sulub', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (4892, 'Edwin', 'Pérez', 8)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3279, 'Ramiro', 'Soberanis', 8)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8436, 'Ileana', 'López', 3)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8356, 'José', 'Sosa', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8427, 'Jesús', 'Monforte', 8)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6289, 'Lucía', 'Moreno', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7423, 'Rocío', 'Molina', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3878, 'Pablo', 'Macías', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1789, 'Margarita', 'Poumián', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6773, 'Erika', 'López', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2952, 'Moisés', 'Alpuche', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6833, 'Miguel', 'Zárate', 8)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8753, 'Alexia', 'Millán', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7321, 'Leonardo', 'Miquel', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7453, 'David', 'Ayala', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9834, 'Alberto', 'Basto', 3)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3734, 'Felipe', 'Carballo', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7322, 'Manuel', 'Alemán', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6219, 'Carolina', 'Mejía', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1905, 'María', 'Basulto', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1284, 'Ramiro', 'Valenzuela', 3)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9350, 'Xótchil', 'Vargas', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (5120, 'Fernando', 'Montalvo', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6447, 'Ricardo', 'Mondragón', 2)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1232, 'Robledo', 'Joan', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (4124, 'Marcos', 'Gallo', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3716, 'Teresa', 'Casado', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (8138, 'David', 'Requena', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2586, 'Irene', 'Pallas', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1615, 'Vannia', 'Cano', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2686, 'José', 'Arques', 3)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2662, 'Adrián', 'Jurado', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9366, 'Lucía', 'Bustamante', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6789, 'Rebeca', 'Zárate', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2286, 'Raquel', 'Bronson', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9923, 'Catarina', 'Infante', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3558, 'Apolonia', 'Duarte', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9720, 'Efrén', 'Pasquel', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1637, 'Sergio', 'Yunes', 8)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3114, 'Natanael', 'Campos', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (5018, 'Noella', 'Borrego', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6874, 'Margarita', 'Betancur', 8)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1599, 'Silvia', 'Quiroz', 7)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (6546, 'Nicolás', 'Echeverría', 1)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (1466, 'Xavier', 'Garza', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7180, 'Roque', 'Reyes', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (3704, 'Héctor', 'Rufino', 5)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (5185, 'Alberto', 'Montealbán', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9618, 'Rafael', 'Méndez', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2729, 'Oscar', 'Muñoz', 6)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2770, 'Víctor', 'Estevez', 5)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (5276, 'Manuel', 'Melo', 5)")

            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (9285, 'Mauricio', 'Siqueiros', 2)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (4516, 'Lether', 'Balam', 0)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (7761, 'Donatiuh', 'Cepeda', 4)")
            db.execSQL("INSERT INTO professors (id, fname, lname, category_id) VALUES (2267, 'Leslie', 'Bolaños', 2)")

            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}
