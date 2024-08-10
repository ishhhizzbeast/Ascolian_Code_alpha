package com.example.ascolian.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ascolian.domain.GalleryImage
import com.example.ascolian.domain.usecases.FetchGalleryImagesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val fetchGalleryImagesUseCase: FetchGalleryImagesUseCases
) : ViewModel() {
    private val _imagesByCategory = MutableStateFlow<Map<String, List<GalleryImage>>>(emptyMap())
    val imagesByCategory: StateFlow<Map<String, List<GalleryImage>>> = _imagesByCategory

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchImages(listOf("Musical Event", "Sports program", "workshops", "Campus Things", "saraswati puja", "Other"))
    }

    private fun fetchImages(categories: List<String>) {
        viewModelScope.launch {
            _isLoading.value = true

            val result = mutableMapOf<String, List<GalleryImage>>()

            // Use async for concurrent fetching
            val deferredResults = categories.map { category ->
                async {
                    try {
                        val images = fetchGalleryImagesUseCase(category)
                        category to images // Return a Pair
                    } catch (e: Exception) {
                        Log.e("GalleryViewModel", "Error fetching images for $category: ${e.message}")
                        category to emptyList() // Handle error
                    }
                }
            }

            // Await all results
            deferredResults.awaitAll().forEach { (category, images) ->
                result[category] = images
            }

            _imagesByCategory.value = result
            _isLoading.value = false
        }
    }
}
