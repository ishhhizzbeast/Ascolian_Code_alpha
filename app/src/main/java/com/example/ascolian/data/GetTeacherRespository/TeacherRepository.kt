package com.example.ascolian.data.GetTeacherRespository

import com.example.ascolian.domain.Teacher


interface TeacherRepository {
    suspend fun getTeachersByDepartment(department: String): List<Teacher>
}