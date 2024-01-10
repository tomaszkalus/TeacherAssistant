package com.example.teacherassistant.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudyClassWithStudents(
    @Embedded val studyClass: StudyClass,
    @Relation(
        parentColumn = "studyClassId",
        entityColumn = "studentId",
        associateBy = Junction(StudentClassCrossRef::class)
    )
    val students: List<Student>
)