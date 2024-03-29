package com.example.teacherassistant.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teacherassistant.model.Student
import com.example.teacherassistant.model.StudentClassCrossRef
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [StudyClass::class, Student::class, StudentClassCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studyClassDao(): StudyClassDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context, scope: CoroutineScope
        ): AppDatabase {
            Log.d("dbx", "getDatabase called")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "teacher_assistant_db"
                ).fallbackToDestructiveMigration().addCallback(AppDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            Log.d("dbx", "Database onCreate called")
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.studyClassDao(), database.studentDao())
                }
            }
        }

        suspend fun populateDatabase(studyClassDao: StudyClassDao, studentDao: StudentDao){
            studyClassDao.deleteAll()

            var studyClass = StudyClass("Aplikacje mobilne gr. 2")
            studyClassDao.insertAll(studyClass)

            studyClass = StudyClass("Programowanie")
            studyClassDao.insertAll(studyClass)

            studyClass = StudyClass("Algorytmy i Struktury Danych gr. 1 piątek")
            studyClassDao.insertAll(studyClass)

            studentDao.deleteAll()

            studentDao.insertAll(Student("Jan", "Kowalski"))
            studentDao.insertAll(Student("Tomasz", "Nowak"))
            studentDao.insertAll(Student("John", "Smith"))
        }
    }


}