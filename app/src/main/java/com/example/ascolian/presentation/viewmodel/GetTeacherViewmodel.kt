package com.example.ascolian.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ascolian.domain.Teacher
import com.example.ascolian.domain.usecases.GetTeacherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTeacherViewModel @Inject constructor(
    private val getTeacherUseCases: GetTeacherUseCases
): ViewModel() {
    private val _teachersByDepartment = MutableStateFlow<Map<String, List<Teacher>>>(emptyMap())
    val teachersByDepartment: StateFlow<Map<String, List<Teacher>>> = _teachersByDepartment

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchTeachers(listOf("Botany", "Chemistry", "Computer", "Environment", "Physics", "Zoology", "Microbiology", "Mathematics"))
    }
    fun fetchTeachers(departments: List<String>) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = mutableMapOf<String, List<Teacher>>()
            departments.forEach { department ->
                val teachers = getTeacherUseCases(department)
                result[department] = teachers
            }
            _teachersByDepartment.value = result
            _isLoading.value = false
        }
    }
}
