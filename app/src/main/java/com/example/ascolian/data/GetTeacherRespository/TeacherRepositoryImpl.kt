package com.example.ascolian.data.GetTeacherRespository

import com.example.ascolian.domain.Teacher
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : TeacherRepository {
    private val db = firebaseDatabase.reference

    override suspend fun getTeachersByDepartment(department: String): List<Teacher> {
        val teachers = mutableListOf<Teacher>()
        val snapshot = db.child("teachers").child(department).get().await()

        for (dataSnapshot in snapshot.children) {
            val teacher = dataSnapshot.getValue<Teacher>()
            if (teacher != null) {
                teachers.add(teacher)
            }
        }

        return teachers
    }
}