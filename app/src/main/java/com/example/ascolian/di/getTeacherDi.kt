package com.example.ascolian.di

import com.example.ascolian.data.GetTeacherRespository.TeacherRepository
import com.example.ascolian.data.GetTeacherRespository.TeacherRepositoryImpl
import com.example.ascolian.domain.usecases.GetTeacherUseCases
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object getTeacherDi {

    @Provides
    @Singleton
    fun providegetTeacherRepository(
        firebaseDatabase: FirebaseDatabase
    ): TeacherRepository {
        return TeacherRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideGetTeacherUsecases(teacherRepository: TeacherRepository):GetTeacherUseCases{
        return GetTeacherUseCases(teacherRepository)
    }
}
