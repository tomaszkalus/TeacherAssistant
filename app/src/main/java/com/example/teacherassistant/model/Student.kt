package com.example.teacherassistant.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "student")
data class Student(
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String,
    @PrimaryKey(autoGenerate = true) val studentId: Int? = null,
)