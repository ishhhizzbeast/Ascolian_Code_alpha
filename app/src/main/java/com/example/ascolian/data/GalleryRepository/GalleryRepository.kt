package com.example.ascolian.data.GalleryRepository

import com.example.ascolian.domain.GalleryImage

interface GalleryRepository {
    suspend fun fetchGalleryImages(category: String): List<GalleryImage>
}
