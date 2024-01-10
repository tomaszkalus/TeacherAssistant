package com.example.teacherassistant.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.teacherassistant.model.Student
import com.example.teacherassistant.model.StudentWithStudyClasses
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM student")
    fun getAll(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE studentId IN (:studentId)")
    suspend fun loadAllByIds(studentId: IntArray): List<Student>

    @Query("SELECT * FROM student WHERE studentId = (:studentId)")
    suspend fun getById(studentId: Int): Student


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM student")
    fun getStudentsWithStudyClasses(): List<StudentWithStudyClasses>
}