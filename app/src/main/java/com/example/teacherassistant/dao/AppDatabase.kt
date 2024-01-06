package com.example.teacherassistant.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [StudyClass::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    //    abstract fun studentDao(): StudentDao
    abstract fun studyClassDao(): StudyClassDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context, scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "teacher_assistant_db"
                ).fallbackToDestructiveMigration().addCallback(AppDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.studyClassDao())
                }
            }
        }

        suspend fun populateDatabase(studyClassDao: StudyClassDao) {
            studyClassDao.deleteAll()

            var studyClass = StudyClass("Aplikacje mobilne gr. 2")
            studyClassDao.insertAll(studyClass)

            studyClass = StudyClass("Programowanie")
            studyClassDao.insertAll(studyClass)

            studyClass = StudyClass("Algorytmy i Struktury Danych gr. 1 piątek")
            studyClassDao.insertAll(studyClass)
        }
    }


}