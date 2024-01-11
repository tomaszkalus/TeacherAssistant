package com.example.teacherassistant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.dao.StudentRepository
import com.example.teacherassistant.dao.StudyClassRepository
import com.example.teacherassistant.model.Student
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.launch

class NewStudentViewModel(private val studentRepository: StudentRepository,
    private val studyClassRepository: StudyClassRepository) : ViewModel() {

    val allStudents: LiveData<List<Student>> = studentRepository.allStudents.asLiveData()
    val allStudyClasses = studyClassRepository.allStudyClasses.asLiveData()

    fun insert(student: Student) = viewModelScope.launch {
        studentRepository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch {
        studentRepository.delete(student)
    }

//    fun insertWithStudyClasses(student: Student, studyClasses: List<StudyClass>) = viewModelScope.launch {
//        studentRepository.insertWithStudyClasses(student, studyClasses)
//    }

}

class NewStudentViewModelFactory(private val studentRepository: StudentRepository,
                                 private val studyClassRepository: StudyClassRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewStudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewStudentViewModel(studentRepository, studyClassRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}