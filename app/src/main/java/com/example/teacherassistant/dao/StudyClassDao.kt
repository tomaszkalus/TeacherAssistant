package com.example.teacherassistant.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.teacherassistant.model.StudyClass
import com.example.teacherassistant.model.StudyClassWithStudents
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyClassDao {
    @Query("SELECT * FROM study_class")
    fun getAll(): Flow<List<StudyClass>>

    @Query("SELECT * FROM study_class WHERE studyClassId IN (:studyClassIds)")
    suspend fun loadAllByIds(studyClassIds: IntArray): List<StudyClass>

    @Query("SELECT * FROM study_class WHERE studyClassId = (:studyClassId)")
    suspend fun getById(studyClassId: Int): StudyClass

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg studyClasses: StudyClass)

    @Delete
    suspend fun delete(studyClass: StudyClass)

    @Query("DELETE FROM study_class")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM study_class")
    fun getStudyClassesWithStudents(): List<StudyClassWithStudents>
}