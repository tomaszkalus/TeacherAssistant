package com.example.teacherassistant.dao

import androidx.annotation.WorkerThread
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class StudyClassRepository(private val studyClassDao: StudyClassDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allStudyClasses: Flow<List<StudyClass>> = studyClassDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(studyClass: StudyClass) {
        studyClassDao.insertAll(studyClass)
    }
}