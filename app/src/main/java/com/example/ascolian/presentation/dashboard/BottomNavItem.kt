package com.example.ascolian.presentation.dashboard

import com.example.ascolian.R

enum class BottomNavItem(val label: String, val route: String, val iconRes: Int) {

    Home("Home", "home", R.drawable.home),
    Notice("Notice", "notice", R.drawable.notice),
    Gallery("Gallery", "gallery", R.drawable.gallery),
    Faculty("Faculty", "faculty", R.drawable.faculty),
    About("About", "about", R.drawable.about);

    companion object {
        fun fromRoute(route: String): BottomNavItem = when (route) {
            Home.route -> Home
            Notice.route -> Notice
            Gallery.route -> Gallery
            Faculty.route -> Faculty
            About.route -> About
            else -> Home
        }
    }
}
