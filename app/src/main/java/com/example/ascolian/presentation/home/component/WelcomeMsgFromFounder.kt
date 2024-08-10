package com.example.ascolian.presentation.home.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.ascolian.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeMsgFromFounder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image Card section at the center
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.size(300.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.amritprashadlegend),
                contentDescription = "Amrit Prasad Pradhan",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Welcome to The College",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Expandable card for the welcome message
        val expanded = remember { mutableStateOf(false) }
        val expandAnimation: State<Float> = animateFloatAsState(
            targetValue = if (expanded.value) 1f else 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value }
                .padding(horizontal = 6.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title inside expandable card
                Text(
                    text = "Amrit Campus, formally named as Public Science College (PUSCOL) and later named as Amrit Science College (ASCOL), after late Mr. Amrit Prasad Pradhan.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // Animated expandable content
                if (expanded.value) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mr Pradhan was born in 1918 at Thamel, Kathmandu. He served as headmaster of Jooddha High School in Birgunj for two years and later joined at Tri-Chandra College as lecturer in Chemistry. In 1962, he became founder Principal of Public Science College (present Amrit Campus) and began teaching as professor of Chemistry.Late Principal Amrit Prasad Pradhan established Amrit Campus with a view to promote the study of Science and Technology in Nepal. The campus has benefited greatly from his spirit of enterprise, dedication, and enthusiasm. While on a mission to the United States on a cause connected with the college, he died in an air crash over Mt. Blanc on January 24, 1966. The death of this noble soul was mourned by the nation.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 0.dp, max = expandAnimation.value * 540.dp)
                            .alpha(expandAnimation.value)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(text = if (expanded.value) "See less..." else "See more...")
                }
            }
        }
    }
}

