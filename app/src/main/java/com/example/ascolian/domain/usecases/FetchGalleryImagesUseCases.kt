package com.example.ascolian.domain.usecases

import com.example.ascolian.data.GalleryRepository.GalleryRepository
import com.example.ascolian.domain.GalleryImage
import javax.inject.Inject

class FetchGalleryImagesUseCases @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(category: String): List<GalleryImage> {
        return repository.fetchGalleryImages(category)
    }
}