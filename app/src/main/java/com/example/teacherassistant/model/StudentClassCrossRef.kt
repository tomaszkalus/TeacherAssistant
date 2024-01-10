package com.example.teacherassistant.model

import androidx.room.Entity

@Entity(tableName = "student_class_cross_ref", primaryKeys = ["studentId", "studyClassId"])
data class StudentClassCrossRef(
//    @PrimaryKey(autoGenerate = true) val studentStudyClassId: Int,
    val studentId: Int,
    val studyClassId: Int,
)