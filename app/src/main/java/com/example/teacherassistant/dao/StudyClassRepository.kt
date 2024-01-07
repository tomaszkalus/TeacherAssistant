package com.example.teacherassistant.dao

import androidx.annotation.WorkerThread
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class StudyClassRepository(private val studyClassDao: StudyClassDao) {
    val allStudyClasses: Flow<List<StudyClass>> = studyClassDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(studyClass: StudyClass) {
        studyClassDao.insertAll(studyClass)
    }
}