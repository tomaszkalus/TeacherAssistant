package com.example.teacherassistant.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.dao.StudyClassRepository
import com.example.teacherassistant.model.StudyClass
import kotlinx.coroutines.launch

class StudyClassViewModel(private val repository: StudyClassRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allStudyClasses: LiveData<List<StudyClass>> = repository.allStudyClasses.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(studyClass: StudyClass) = viewModelScope.launch {
        repository.insert(studyClass)
    }
}

class StudyClassViewModelFactory(private val repository: StudyClassRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudyClassViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudyClassViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}