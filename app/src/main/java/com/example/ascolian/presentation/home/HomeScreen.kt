package com.example.ascolian.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ascolian.presentation.home.component.HorizontalPagerWithAutoScroll
import com.example.ascolian.presentation.home.component.MapComposable
import com.example.ascolian.presentation.home.component.NotableAlumniComposable
import com.example.ascolian.presentation.home.component.OurCoursesScreen
import com.example.ascolian.presentation.home.component.StatisticsScreen
import com.example.ascolian.presentation.home.component.TeacherStatisticsScreen
import com.example.ascolian.presentation.home.component.WelcomeMsgFromFounder


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // LazyColumn to contain different composables
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                HorizontalPagerWithAutoScroll()
                WelcomeMsgFromFounder()
                OurCoursesScreen()
                StatisticsScreen()
                TeacherStatisticsScreen()
                NotableAlumniComposable()
                MapComposable()
            }
            // Add more items or composables here
        }
    }
}


