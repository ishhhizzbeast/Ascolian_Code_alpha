package com.example.ascolian.presentation.notice

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ascolian.R
import com.example.ascolian.Utils.PacmanIndicator
import com.example.ascolian.ZoomImageActivity
import com.example.ascolian.domain.Notice
import com.example.ascolian.presentation.viewmodel.GetNoticesViewModel

@Composable
fun NoticeScreen() {
    val context = LocalContext.current
    val viewModel: GetNoticesViewModel = hiltViewModel()
    val notices by viewModel.notices.collectAsState()
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xffF9F7F7))) {
        val isLoading by viewModel.isLoading.collectAsState()

        if (isLoading) {
            // Show Pacman loading indicator
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)) // Semi-transparent black overlay
                    .clickable(enabled = false) {}, // Prevent interactions
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
                    .padding(12.dp)
            ) {
                items(notices) { notice ->
                    NoticeCard(
                        notice = notice,
                        onImageClick = {
                            // Launch the new activity to show the image with zoom feature
                            val intent = Intent(context, ZoomImageActivity::class.java).apply {
                                putExtra("imageUrl", notice.imageURL)
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun NoticeCard(
    notice: Notice,
    onImageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(400.dp)
        ,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ascollogo),
                    contentDescription = "Ascol logo",
                    modifier = Modifier
                        .size(60.dp) // Adjust size as needed
                        .clip(CircleShape)
                        .border(2.dp, Color(0xff867070), CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(text = "Ascol admin", style = MaterialTheme.typography.titleMedium)
                    Text(text = "${notice.date}, ${notice.time}", style = MaterialTheme.typography.labelMedium)
                }
            }
            HorizontalDivider(Modifier.padding(horizontal = 8.dp, vertical = 4.dp), thickness = 2.dp)
            Text(text = notice.title, style = MaterialTheme.typography.bodyLarge, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(model = notice.imageURL),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners for the image
                    .clickable(onClick = onImageClick),
                contentScale = ContentScale.Crop
            )
        }
    }
}
