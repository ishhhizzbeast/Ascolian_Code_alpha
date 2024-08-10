package com.example.ascolian.domain.usecases

import com.example.ascolian.data.GetTeacherRespository.TeacherRepository
import com.example.ascolian.domain.Teacher
import javax.inject.Inject


class GetTeacherUseCases @Inject constructor(
    private val teacherRepository: TeacherRepository
){
    suspend operator fun invoke(department:String):List<Teacher>{
        return teacherRepository.getTeachersByDepartment(department)
    }
}