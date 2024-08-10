package com.example.ascolian.data.GalleryRepository

import com.example.ascolian.domain.GalleryImage
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : GalleryRepository {
     private val database = firebaseDatabase.reference

    override suspend fun fetchGalleryImages(category: String): List<GalleryImage> {
        val imageUrls = mutableListOf<GalleryImage>()
        val snapshot = database.child("gallery").child(category).get().await()
        for (imageSnapshot in snapshot.children) {
            // Convert snapshot to GalleryImage object
            val galleryImage = imageSnapshot.getValue(GalleryImage::class.java)
            // Check if galleryImage is not null and then add the imageUrl to the list
            if(galleryImage != null) {
                imageUrls.add(galleryImage) // Extract the imageUrl
            }
        }
        return imageUrls
    }
}