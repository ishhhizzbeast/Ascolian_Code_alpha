package com.example.ascolian.di

import com.example.ascolian.data.NoticeRepository.NoticeRepository
import com.example.ascolian.data.NoticeRepository.NoticeRepositoryImpl
import com.example.ascolian.domain.usecases.NoticeUseCases
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoticeModule {

    @Provides
    @Singleton
    fun provideNoticeRepository(
        firebaseDatabase: FirebaseDatabase
    ): NoticeRepository {
        return NoticeRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideNoticeUseCases(
        repository: NoticeRepository
    ): NoticeUseCases {
        return NoticeUseCases(repository)
    }
}