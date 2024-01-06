package com.example.teacherassistant.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_class")
data class StudyClass(
    @ColumnInfo val name: String?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)