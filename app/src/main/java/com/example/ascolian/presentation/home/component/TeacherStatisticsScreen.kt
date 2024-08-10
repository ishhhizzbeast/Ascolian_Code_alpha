package com.example.ascolian.presentation.home.component
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars

@Composable
fun TeacherStatisticsScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp) // Adjust height as necessary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = "Teacher Statistics",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(10.dp)
            )

            // Bar Chart Data
            val subjects = listOf(
                "Botany",
                "Chemistry",
                "Computer",
                "Environment",
                "M.B.",
                "Phy.",
                "Stat.",
                "Zool."
            )
            val values = listOf(18.0, 39.0, 4.0, 5.0, 3.0, 35.0, 7.0, 21.0)

            // Create the ColumnChart
            ElevatedCard(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xffe6e6e6))
            ) {
                ColumnChart(
                    modifier = Modifier
                        .fillMaxHeight(.8f)
                        .padding(all = 7.dp),
                    data = subjects.mapIndexed { index, subject ->
                        Bars(
                            label = subject,
                            values = listOf(
                                Bars.Data(
                                    value = values[index], color = Brush.verticalGradient(
                                        colors = listOf(Color.Blue, Color.Cyan)
                                    )
                                )
                            )
                        )
                    },
                    barProperties = BarProperties(
                        spacing = 1.dp,
                        thickness = 10.dp
                    ),
                    animationSpec = tween(
                       durationMillis = 5000, easing = FastOutSlowInEasing
                    ),
                    animationDelay = 1000L
                )
            }

        }
    }
}
