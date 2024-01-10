package com.example.teacherassistant.dao

import androidx.annotation.WorkerThread
import com.example.teacherassistant.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    val allStudents: Flow<List<Student>> = studentDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(student: Student) {
        studentDao.insertAll(student)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(student: Student) {
        studentDao.delete(student)
    }
}