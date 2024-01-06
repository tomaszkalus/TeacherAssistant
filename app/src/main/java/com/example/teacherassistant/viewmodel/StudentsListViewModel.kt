package com.example.teacherassistant.viewmodel

import androidx.lifecycle.ViewModel

class StudentsListViewModel: ViewModel() {
    data class Student(val firstName: String, val lastName: String)

    private val _students = arrayOf(
        Student("Tomasz", "Kalus"),
        Student("Adam", "Nowak"),
        Student("Janusz", "Kowalski")
    )

    val students: Array<Student> get() = _students
}