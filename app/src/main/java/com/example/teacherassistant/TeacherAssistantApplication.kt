package com.example.teacherassistant

import android.app.Application
import com.example.teacherassistant.dao.AppDatabase
import com.example.teacherassistant.dao.StudentRepository
import com.example.teacherassistant.dao.StudyClassRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TeacherAssistantApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StudyClassRepository(database.studyClassDao()) }
    val studentRepository by lazy { StudentRepository(database.studentDao()) }
}