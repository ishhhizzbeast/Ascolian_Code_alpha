package com.example.ascolian.presentation.home.component


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapComposable() {
    val markerState = rememberMarkerState(position = LatLng(27.717797, 85.312806))
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.Builder()
            .target(LatLng(27.717797, 85.312806)) // Coordinates of Amrit Science Campus
            .zoom(15f)
            .build()
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
    VerticalDivider(
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.height(10.dp)
    )
    Text(
        text = "Find us on map 🗺️",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
    )
    VerticalDivider(
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.height(10.dp)
    )
        Spacer(modifier =Modifier.height(4.dp))
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                // Open Google Maps Navigation
                val gmmIntentUri = Uri.parse("google.navigation:q=27.717797,85.312806")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(context, mapIntent, null)
            },
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = markerState,
                    title = "Amrit Science Campus"
                )
            }
        }
    }
}


