package com.example.ascolian.presentation.gallery

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ascolian.R
import com.example.ascolian.Utils.PacmanIndicator
import com.example.ascolian.domain.GalleryImage
import com.example.ascolian.presentation.gallery.component.GalleryCategoryCard
import com.example.ascolian.presentation.viewmodel.GalleryViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun GalleryScreen() {
    val viewModel: GalleryViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val imagesByCategory = mapOf(
        "Musical Event" to listOf(
            GalleryImage(R.drawable.musical2),
            GalleryImage(R.drawable.musical1),
            GalleryImage(R.drawable.musical4),
            GalleryImage(R.drawable.musical5),
            GalleryImage(R.drawable.musical6),
            GalleryImage(R.drawable.musical7),
        ),
        "Sports program" to listOf(
            GalleryImage(R.drawable.sport1),
            GalleryImage(R.drawable.sport2),
            GalleryImage(R.drawable.sport3),
            GalleryImage(R.drawable.sport4),
            GalleryImage(R.drawable.sport5),
            GalleryImage(R.drawable.sport6),
            GalleryImage(R.drawable.sport7),
        ),
        "Workshops" to listOf(
            GalleryImage(R.drawable.workshop),
            GalleryImage(R.drawable.workshop1),
            GalleryImage(R.drawable.workshop2),
            GalleryImage(R.drawable.workshop3),
            GalleryImage(R.drawable.workshop4),
            GalleryImage(R.drawable.workshop5),
            GalleryImage(R.drawable.workshop6),
        ),
        "Ascol Day" to listOf(
            GalleryImage(R.drawable.ascol1),
            GalleryImage(R.drawable.ascol2),
            GalleryImage(R.drawable.ascol3),
            GalleryImage(R.drawable.ascol4),
            GalleryImage(R.drawable.ascol5),
            GalleryImage(R.drawable.ascol6),
            GalleryImage(R.drawable.ascol7),
        ),
        "Other Events" to listOf(
            GalleryImage(R.drawable.other1),
            GalleryImage(R.drawable.other2),
            GalleryImage(R.drawable.other3),
            GalleryImage(R.drawable.other4),
            GalleryImage(R.drawable.other5),
            GalleryImage(R.drawable.other6),
            GalleryImage(R.drawable.other7),
        ),


    )
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)),
                contentAlignment = Alignment.Center
            ) {
                PacmanIndicator(
                    color = MaterialTheme.colorScheme.inversePrimary,
                    ballDiameter = 60f,
                    canvasSize = 60.dp,
                    animationDuration = 1000,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(imagesByCategory.entries.toList()) { (category, images) ->
                    GalleryCategoryCard(category, images)
                }
            }
        }
    }
}
