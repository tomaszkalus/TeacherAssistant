package com.example.teacherassistant.dao

import androidx.annotation.WorkerThread
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.flow.Flow

class StudyClassRepository(private val studyClassDao: StudyClassDao) {
    val allStudyClasses: Flow<List<StudyClass>> = studyClassDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(studyClass: StudyClass) {
        studyClassDao.insertAll(studyClass)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(studyClass: StudyClass) {
        studyClassDao.delete(studyClass)
    }
}