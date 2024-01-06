package com.example.teacherassistant

import android.app.Application
import com.example.teacherassistant.dao.AppDatabase
import com.example.teacherassistant.dao.StudyClassRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TeacherAssistantApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StudyClassRepository(database.studyClassDao()) }
}