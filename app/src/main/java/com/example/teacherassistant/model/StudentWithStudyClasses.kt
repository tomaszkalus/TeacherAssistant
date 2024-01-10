package com.example.teacherassistant.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithStudyClasses(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "studyClassId",
        associateBy = Junction(StudentClassCrossRef::class)
    )
    val studyClasses: List<StudyClass>
)