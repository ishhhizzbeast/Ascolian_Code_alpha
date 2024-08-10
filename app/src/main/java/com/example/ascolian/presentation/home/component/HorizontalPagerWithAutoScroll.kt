package com.example.ascolian.presentation.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ascolian.R
import com.example.ascolian.Utils.DotIndicators
import com.example.ascolian.Utils.carouselTransition
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithAutoScroll() {

    val images = listOf(R.drawable.ascolbuilding, R.drawable.staff, R.drawable.siderimag2,R.drawable.purposedbuilding,R.drawable.foregroundsider)
    val titles = listOf("Majestic View of Ascol's Serenity", "A Glimpse into the Heart of PUSCOL", "Ascol's Splendor from the Boys' Hostel","Vision of the Future: The New Ascol Building","Unveiling the Hidden Beauty from Ascol")
    val pagerState = rememberPagerState(pageCount = {images.size}, initialPage = images[0])
    val autoScrollDuration = 3000L

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        // Auto-scrolling effect
        LaunchedEffect(pagerState.currentPage) {
            delay(autoScrollDuration)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 10.dp)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp
        ){page->
            CarouselItem(imageRes = images[page], title = titles[page], modifier = Modifier.carouselTransition(page,pagerState))
        }
        // Dot indicators
        DotIndicators(
            pageCount = images.size,
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }

}
@Composable
fun CarouselItem(imageRes: Int, title: String,modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
             .height(300.dp)
            .clip(RoundedCornerShape(16.dp)) // Optional: Rounded corners
            .background(Color.Gray) // Fallback color while loading
    ) {
        // Image background
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Vertical gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 65f,
                         // Adjust based on your image size
                    )
                )
        )

        // Text overlay
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp) // Padding for text
        )
    }
}