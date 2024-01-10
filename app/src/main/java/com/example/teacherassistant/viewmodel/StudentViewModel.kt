package com.example.teacherassistant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.dao.StudentRepository
import com.example.teacherassistant.model.Student
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}

class StudentViewModelFactory(private val repository: StudentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}