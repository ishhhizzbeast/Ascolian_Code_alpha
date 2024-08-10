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
fun StatisticsScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
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
                    text = "Student Statistics",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                VerticalDivider(
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.height(10.dp)
                )
                ElevatedCard(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xffe6e6e6))
                ) {

                ColumnChart(
                    modifier = Modifier
                        .fillMaxHeight(.8f)
                        .padding(all = 7.dp),
                    data = listOf(
                        Bars(
                            label = "MIT",
                            values = listOf(
                                Bars.Data(
                                    label = "MIT",
                                    value = 34.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Blue, Color.Cyan)
                                    )
                                )
                            )
                        ),
                        Bars(
                            label = "BSCCSIT",
                            values = listOf(
                                Bars.Data(
                                    label = "BSCCSIT",
                                    value = 750.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Green, Color(0xff32CD32))
                                    )
                                )
                            )
                        ),
                        Bars(
                            label = "BIT",
                            values = listOf(
                                Bars.Data(
                                    label = "BIT",
                                    value = 134.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Magenta, Color(0xffFFC0CB))
                                    )
                                )
                            )
                        ),
                        Bars(
                            label = "BSC",
                            values = listOf(
                                Bars.Data(
                                    label = "BSC",
                                    value = 1800.0,
                                    color = Brush.verticalGradient(
                                        colors = listOf(Color.Red, Color(0xffFFA500))
                                    )
                                )
                            )
                        )
                    ),
                    barProperties = BarProperties(
                        cornerRadius = Bars.Data.Radius.Rectangle(topRight = 6.dp, topLeft = 6.dp),
                        spacing = 16.dp, // Increased spacing for better visibility
                        thickness = 40.dp // Adjusted stroke width for better representation
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
