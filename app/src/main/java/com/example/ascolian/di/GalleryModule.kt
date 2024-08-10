package com.example.ascolian.di

import com.example.ascolian.data.GalleryRepository.GalleryRepository
import com.example.ascolian.data.GalleryRepository.GalleryRepositoryImpl
import com.example.ascolian.domain.usecases.FetchGalleryImagesUseCases
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GalleryModule {

    @Provides
    @Singleton
    fun providegetGalleryRepository(
        firebaseDatabase: FirebaseDatabase
    ): GalleryRepository {
        return GalleryRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun provideGalleryUsecases(galleryRepository: GalleryRepository): FetchGalleryImagesUseCases {
        return FetchGalleryImagesUseCases(galleryRepository)
    }
}
