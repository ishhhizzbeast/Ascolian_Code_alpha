package com.example.ascolian

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ascolian.presentation.aboutus.AboutScreen
import com.example.ascolian.presentation.dashboard.BottomNavItem
import com.example.ascolian.presentation.dashboard.MainScreen
import com.example.ascolian.presentation.faculty.FacultyScreen
import com.example.ascolian.presentation.gallery.GalleryScreen
import com.example.ascolian.presentation.home.HomeScreen
import com.example.ascolian.presentation.notice.NoticeScreen
import com.example.compose.AscolianTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AscolianTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val selectedPage = remember { mutableStateOf(BottomNavItem.Home) }

                    NavHost(navController, startDestination = BottomNavItem.Home.route) {
                        composable(BottomNavItem.Home.route) { HomeScreen() }
                        composable(BottomNavItem.Notice.route) { NoticeScreen() }
                        composable(BottomNavItem.Gallery.route) { GalleryScreen() }
                        composable(BottomNavItem.Faculty.route) { FacultyScreen() }
                        composable(BottomNavItem.About.route) { AboutScreen() }
                    }

                    MainScreen(navController = navController, selectedPage = selectedPage)
                }
            }
        }
    }
}
