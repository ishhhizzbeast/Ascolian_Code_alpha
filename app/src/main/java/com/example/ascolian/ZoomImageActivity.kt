package com.example.ascolian

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter

// ZoomImageActivity.kt
class ZoomImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_image)
        val imageUrl = intent.getStringExtra("imageUrl")

        // Set up the ZoomableImage Composable
        val imageView = findViewById<ComposeView>(R.id.composeView)
        imageView.setContent{
            ZoomableImage(imageUrl ?: "")
        }
    }
}

@Composable
fun ZoomableImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val state = rememberAsyncImagePainter(imageUrl)
    val context = LocalContext.current
    Box(modifier = modifier) {
        Image(
            painter = state,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    // Launch a new activity to show the image in full screen
                    val intent = Intent(context, ZoomImageActivity::class.java).apply {
                        putExtra("imageUrl", imageUrl)
                    }
                    context.startActivity(intent)
                }
        )
    }
}