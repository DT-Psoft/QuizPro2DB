package com.example.quizapppro2.Class

import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapppro2.Class.DAO.CategoryDAO
import com.example.quizapppro2.Class.DAO.UserDAO
import com.example.quizapppro2.Class.DAO.User_ConfigurationDAO
import com.example.quizapppro2.Class.Entities.*

@Database(
    entities = [
        QuestionETY::class, AnswerETY::class,
        LastGame_AnswerETY::class, CategoryETY::class,
        LastGame_QuestionETY::class, LastGameETY::class,
        User_ConfigurationETY::class, UserETY::class,
        ScoreBoardETY::class

    ], version = 2
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun User_ConfigurationDAO(): User_ConfigurationDAO
    abstract fun UserDAO(): UserDAO
    abstract fun getCategoriesDAO (): CategoryDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        private lateinit var currentUser : UserETY
        private lateinit var currentConfiguration: User_ConfigurationETY

        fun getCurrentConfiguration() = currentConfiguration
        fun setCurrentConfiguration(configuration: User_ConfigurationETY){
            currentConfiguration = configuration
        }
        fun getCurrentUser() = currentUser
        fun setCurrentUser(user: UserETY){
            currentUser = user
        }

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "quizzapp.db"
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

            //CATEGORIES
            db.execSQL("INSERT INTO category VALUES (1,'Comics')")
            db.execSQL("INSERT INTO category VALUES (2,'Fisica')")
            db.execSQL("INSERT INTO category VALUES (3,'Cine')")
            db.execSQL("INSERT INTO category VALUES (4,'Matematicas')")
            db.execSQL("INSERT INTO category VALUES (5,'Historia')")
            db.execSQL("INSERT INTO category VALUES (6,'Videojuegos')")

            //QUESTIONS
            db.execSQL("INSERT INTO question VALUES (1,'¿Cuantos Green Lanterns hay que provengan de la tierra?', 1)")
            db.execSQL("INSERT INTO question VALUES (2,'¿Quien fue el segundo robin?', 1)")
            db.execSQL("INSERT INTO question VAlUES (3,'¿Quien usa rayos omega como su movimiento principal?',1)")
            db.execSQL("INSERT INTO question VAlUES (4,'¿Que busca thanos en Infinity War?',1)")
            db.execSQL("INSERT INTO question VAlUES (5,'¿De donde viene Superman?',1)")

            db.execSQL("INSERT INTO question VAlUES (6,'¿Que es el gran atractor?',2)")
            db.execSQL("INSERT INTO question VAlUES (7,'¿Cual no es inestable?',2)")
            db.execSQL("INSERT INTO question VAlUES (8,'¿donde esta el centro del universo?',2)")
            db.execSQL("INSERT INTO question VAlUES (9,'¿Cual es la antiparticula del neutron?',2)")
            db.execSQL("INSERT INTO question VAlUES (10,'¿En una tormenta de rayos en el campo que harias?',2)")

            db.execSQL("INSERT INTO question VAlUES (11,'¿Cuantas peliculas tiene el universo de marvel?',3)")
            db.execSQL("INSERT INTO question VAlUES (12,'¿Cual es la octava pelicula de Quentin Tarantino?',3)")
            db.execSQL("INSERT INTO question VAlUES (13,'¿Los dinosaurios de pusieron de moda gracias a cual pelicula?',3)")
            db.execSQL("INSERT INTO question VAlUES (14,'¿Que estudio hizo la saga de Toy Story?',3)")
            db.execSQL("INSERT INTO question VAlUES (15,'¿El modelo T-800 es el modelo de un robot de que pelicula?',3)")

            db.execSQL("INSERT INTO question VAlUES (16,'¿Quien dijo \"Los matematicos son una maquina que convierten cafe en teorias\"?',4)")
            db.execSQL("INSERT INTO question VAlUES (17,'¿Que es mas grande que el infinito de los numeros naturales?',4)")
            db.execSQL("INSERT INTO question VAlUES (18,'¿Cuanto pesan los oceanos en la tierra?',4)")
            db.execSQL("INSERT INTO question VAlUES (19,'¿Cual de estos problemas sigue abierto?',4)")
            db.execSQL("INSERT INTO question VAlUES (20,'¿cuanto es 2 + 2?',4)")

            db.execSQL("INSERT INTO question VAlUES (21,'¿En que siglo siglo se produjo en europa la mayor epidemia de la peste bubonica?',5)")
            db.execSQL("INSERT INTO question VAlUES (22,'¿Que civilizacion antigua fue la primera en medirla duracion de un año?',5)")
            db.execSQL("INSERT INTO question VAlUES (23,'¿En que año panama se proclama independiente de españa?',5)")
            db.execSQL("INSERT INTO question VAlUES (24,'Durante el siglo XIX ¿Que imperio era el mas grande?',5)")
            db.execSQL("INSERT INTO question VAlUES (25,'¿En que año se produjo la revolucion rusa?',5)")

            db.execSQL("INSERT INTO question VAlUES (26,'¿En que serie de videojuegos aparece Cloud?',6)")
            db.execSQL("INSERT INTO question VAlUES (27,'¿cual de estos juegos es definido por su gran dificultad?',6)")
            db.execSQL("INSERT INTO question VAlUES (28,'¿En que serie aparece el personaje Doomslayer?',6)")
            db.execSQL("INSERT INTO question VAlUES (29,'¿En Mass Effect 3 que final fue el mas importante?',6)")
            db.execSQL("INSERT INTO question VAlUES (30,'¿Que item en Mario Kart es usado para atacar siempre al primer lugar?',6)")

            //ANSWERS 1
            db.execSQL("INSERT INTO answer  VALUES (1,'1', 0, 1)")
            db.execSQL("INSERT INTO answer  VALUES (2,'4', 0, 1)")
            db.execSQL("INSERT INTO answer  VALUES (3,'6', 1, 1)")
            db.execSQL("INSERT INTO answer  VALUES (4,'5', 0, 1)")

            db.execSQL("INSERT INTO answer  VALUES (5,'Jason Todd', 1, 2)")
            db.execSQL("INSERT INTO answer  VALUES (6,'Dick Grayson', 0, 2)")
            db.execSQL("INSERT INTO answer  VALUES (7,'Damian Wayne', 0, 2)")
            db.execSQL("INSERT INTO answer  VALUES (8,'Tim Drake', 0, 2)")

            db.execSQL("INSERT INTO answer  VALUES (9,'Darkside', 1, 3)")
            db.execSQL("INSERT INTO answer  VALUES (10,'Thanos', 0,3)")
            db.execSQL("INSERT INTO answer  VALUES (11,'Terrax', 0,3)")
            db.execSQL("INSERT INTO answer  VALUES (12,'Mongul', 0,3)")

            db.execSQL("INSERT INTO answer  VALUES (13,'Anti-Life Equation', 0, 4)")
            db.execSQL("INSERT INTO answer  VALUES (14,'Infinity stones', 1, 4)")
            db.execSQL("INSERT INTO answer  VALUES (15,'Infinity Gauntlet', 0, 4)")
            db.execSQL("INSERT INTO answer  VALUES (16,'Phoenyx Force', 0, 4)")

            db.execSQL("INSERT INTO answer  VALUES (17,'Krypton', 1, 5)")
            db.execSQL("INSERT INTO answer  VALUES (18,'Metropolis', 0, 5)")
            db.execSQL("INSERT INTO answer  VALUES (19,'Gotham', 0, 5)")
            db.execSQL("INSERT INTO answer  VALUES (20,'Smallville', 0, 5)")

            //ANSWERS 2
            db.execSQL("INSERT INTO answer  VALUES (21,'agujero negro en el centro de la galaxia', 0, 6)")
            db.execSQL("INSERT INTO answer  VALUES (22,'el centro del universo', 0, 6)")
            db.execSQL("INSERT INTO answer  VALUES (23,'region del universo a donde se dirige todo', 1, 6)")
            db.execSQL("INSERT INTO answer  VALUES (24,'fede siendo un iman de pena y deshonra', 0, 6)")

            db.execSQL("INSERT INTO answer  VALUES (25,'el neutron', 0, 7)")
            db.execSQL("INSERT INTO answer  VALUES (26,'el neutrino', 1, 7)")
            db.execSQL("INSERT INTO answer  VALUES (27,'el boson de higgs', 0, 7)")
            db.execSQL("INSERT INTO answer  VALUES (28,'tus sentimientos', 0, 7)")

            db.execSQL("INSERT INTO answer  VALUES (29,'no hay centro especifico', 0, 8)")
            db.execSQL("INSERT INTO answer  VALUES (30,'la frente de samir', 0, 8)")
            db.execSQL("INSERT INTO answer  VALUES (31,'la voz de segovia', 0, 8)")
            db.execSQL("INSERT INTO answer  VALUES (32,'todas son correctas', 1, 8)")

            db.execSQL("INSERT INTO answer  VALUES (33,'No tiene antiparticula', 0, 9)")
            db.execSQL("INSERT INTO answer  VALUES (34,'El antineutron', 1, 9)")
            db.execSQL("INSERT INTO answer  VALUES (35,'el proton', 0, 9)")
            db.execSQL("INSERT INTO answer  VALUES (36,'no se ha descubierto', 0, 9)")

            db.execSQL("INSERT INTO answer  VALUES (37,'subirte al punto mas alto', 0, 10)")
            db.execSQL("INSERT INTO answer  VALUES (38,'Meterte dentro de un coche', 1, 10)")
            db.execSQL("INSERT INTO answer  VALUES (39,'Ponerte debajo de un arbol', 0, 10)")
            db.execSQL("INSERT INTO answer  VALUES (40,'Aceptar tu muerte', 0, 10)")

            //ANSWERS 3
            db.execSQL("INSERT INTO answer  VALUES (41,'22', 1, 11)")
            db.execSQL("INSERT INTO answer  VALUES (42,'10', 0, 11)")
            db.execSQL("INSERT INTO answer  VALUES (43,'15', 0, 11)")
            db.execSQL("INSERT INTO answer  VALUES (44,'20', 0, 11)")

            db.execSQL("INSERT INTO answer  VALUES (45,'The Hateful Eight', 1, 12)")
            db.execSQL("INSERT INTO answer  VALUES (46,'Kill Bill', 0, 12)")
            db.execSQL("INSERT INTO answer  VALUES (47,'Reservoir Dogs', 0, 12)")
            db.execSQL("INSERT INTO answer  VALUES (48,'Django', 0, 12)")

            db.execSQL("INSERT INTO answer  VALUES (49,'Jurassic Park', 1, 13)")
            db.execSQL("INSERT INTO answer  VALUES (50,'King Kong', 0, 13)")
            db.execSQL("INSERT INTO answer  VALUES (51,'Godzilla', 0, 13)")
            db.execSQL("INSERT INTO answer  VALUES (52,'Cars Edicion Samir', 0, 13)")

            db.execSQL("INSERT INTO answer  VALUES (53,'Pixar', 1, 14)")
            db.execSQL("INSERT INTO answer  VALUES (54,'Disney', 0, 14)")
            db.execSQL("INSERT INTO answer  VALUES (55,'Fox', 0, 14)")
            db.execSQL("INSERT INTO answer  VALUES (56,'Universal', 0,14)")

            db.execSQL("INSERT INTO answer  VALUES (57,'Yo, Robot', 0, 15)")
            db.execSQL("INSERT INTO answer  VALUES (58,'Cortocircuito', 0, 15)")
            db.execSQL("INSERT INTO answer  VALUES (59,'Terminator', 1,15)")
            db.execSQL("INSERT INTO answer  VALUES (60,'Robocop', 0, 15)")

            //ANSWERS 4
            db.execSQL("INSERT INTO answer  VALUES (61,'Martin Gardner', 1,16)")
            db.execSQL("INSERT INTO answer  VALUES (62,'Paul Erdos', 0,16)")
            db.execSQL("INSERT INTO answer  VALUES (63,'Albert Einstein', 0,16)")
            db.execSQL("INSERT INTO answer  VALUES (64,'zeta', 0,16)")

            db.execSQL("INSERT INTO answer  VALUES (65,'Infinito+1', 0,17)")
            db.execSQL("INSERT INTO answer  VALUES (66,'2 potencia infinito', 1,17)")
            db.execSQL("INSERT INTO answer  VALUES (67,'2 x infinito', 0,17)")
            db.execSQL("INSERT INTO answer  VALUES (68,'todos son iguales', 0,17)")

            db.execSQL("INSERT INTO answer  VALUES (69,'4000 veces la poblacion de la tierra', 1,18)")
            db.execSQL("INSERT INTO answer  VALUES (70,'poblacion mundial x 2', 0,18)")
            db.execSQL("INSERT INTO answer  VALUES (71,'la mitad de marte', 0,18)")
            db.execSQL("INSERT INTO answer  VALUES (72,'el mismo peso de mi orgullo',0, 18)")

            db.execSQL("INSERT INTO answer  VALUES (73,'La cuadratura del circulo', 0,19)")
            db.execSQL("INSERT INTO answer  VALUES (74,'que todos los numeros son la suma de 3 capicuas', 0,19)")
            db.execSQL("INSERT INTO answer  VALUES (75,'que todos los numeros son la suma de dos primos', 1,19)")
            db.execSQL("INSERT INTO answer  VALUES (76,'mi falta de amor propio', 0,19)")

            db.execSQL("INSERT INTO answer  VALUES (77,'4', 1,20)")
            db.execSQL("INSERT INTO answer  VALUES (78,'5', 0,20)")
            db.execSQL("INSERT INTO answer  VALUES (79,'oreo', 0,20)")
            db.execSQL("INSERT INTO answer  VALUES (80,'+un patio+', 0,20)")

            //ANSWERS 5
            db.execSQL("INSERT INTO answer  VALUES (81,'Mayas', 0,21)")
            db.execSQL("INSERT INTO answer  VALUES (82,'Egipcios', 1,21)")
            db.execSQL("INSERT INTO answer  VALUES (83,'Aztecas', 0,21)")
            db.execSQL("INSERT INTO answer  VALUES (84,'Batman', 0,21)")

            db.execSQL("INSERT INTO answer  VALUES (85,'XII', 0,22)")
            db.execSQL("INSERT INTO answer  VALUES (86,'XIV', 1,22)")
            db.execSQL("INSERT INTO answer  VALUES (87,'XVI', 0,22)")
            db.execSQL("INSERT INTO answer  VALUES (88,'oreo', 0,22)")

            db.execSQL("INSERT INTO answer  VALUES (89,'1821', 1, 23)")
            db.execSQL("INSERT INTO answer  VALUES (90,'1841', 0, 23)")
            db.execSQL("INSERT INTO answer  VALUES (91,'1881', 0, 23)")
            db.execSQL("INSERT INTO answer  VALUES (92,'2000', 0, 23)")

            db.execSQL("INSERT INTO answer  VALUES (93,'Imperio Aleman', 0,24)")
            db.execSQL("INSERT INTO answer  VALUES (94,'Imperio Español', 0,24)")
            db.execSQL("INSERT INTO answer  VALUES (95,'Imperio Britanico', 1,24)")
            db.execSQL("INSERT INTO answer  VALUES (96,'Imperio Galactico', 0,24)")

            db.execSQL("INSERT INTO answer  VALUES (97,'1990', 0,25)")
            db.execSQL("INSERT INTO answer  VALUES (98,'1917', 1,25)")
            db.execSQL("INSERT INTO answer  VALUES (99,'1929', 0,25)")
            db.execSQL("INSERT INTO answer  VALUES (100,'1980', 0,25)")

            //ANSWERS 6
            db.execSQL("INSERT INTO answer  VALUES (101,'Final Fantasy', 1, 26)")
            db.execSQL("INSERT INTO answer  VALUES (102,'Halo', 0,26)")
            db.execSQL("INSERT INTO answer  VALUES (103,'Half Life', 0,26)")
            db.execSQL("INSERT INTO answer  VALUES (104,'Dark Souls', 0,26)")

            db.execSQL("INSERT INTO answer  VALUES (105,'Dark Souls', 1,27)")
            db.execSQL("INSERT INTO answer  VALUES (106,'Super Mario', 0,27)")
            db.execSQL("INSERT INTO answer  VALUES (107,'The Legend of Zelda', 0,27)")
            db.execSQL("INSERT INTO answer  VALUES (108,'Mario Party', 0,27)")

            db.execSQL("INSERT INTO answer  VALUES (109,'Doom', 1,28)")
            db.execSQL("INSERT INTO answer  VALUES (110,'Super Mario', 0,28)")
            db.execSQL("INSERT INTO answer  VALUES (111,'Half Life', 0,28)")
            db.execSQL("INSERT INTO answer  VALUES (112,'Skyrim', 0,28)")

            db.execSQL("INSERT INTO answer  VALUES (113,'Rojo', 0,29)")
            db.execSQL("INSERT INTO answer  VALUES (114,'Verde', 0,29)")
            db.execSQL("INSERT INTO answer  VALUES (115,'Azul', 0,29)")
            db.execSQL("INSERT INTO answer  VALUES (116,'Ninguno', 1,29)")

            db.execSQL("INSERT INTO answer  VALUES (117,'Bananna', 0,30)")
            db.execSQL("INSERT INTO answer  VALUES (118,'Blue Shell', 1,30)")
            db.execSQL("INSERT INTO answer  VALUES (119,'Red Shell', 0,30)")
            db.execSQL("INSERT INTO answer  VALUES (120,'Green Shell', 0,30)")

        }
    }
}