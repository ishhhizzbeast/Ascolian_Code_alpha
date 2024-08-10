package com.example.ascolian.presentation.dashboard


import AnimatedDrawer
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ascolian.R
import com.example.ascolian.Utils.clickableWithoutRipple
import com.example.ascolian.presentation.aboutus.AboutScreen
import com.example.ascolian.presentation.faculty.FacultyScreen
import com.example.ascolian.presentation.gallery.GalleryScreen
import com.example.ascolian.presentation.home.HomeScreen
import com.example.ascolian.presentation.notice.NoticeScreen


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, selectedPage: MutableState<BottomNavItem>) {
    var drawerState by remember { mutableStateOf(DrawerState.CLOSED) }

    AnimatedDrawer(
        backgroundColors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
        ),
        drawerState = drawerState,
        homeContent = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(selectedPage.value.label) },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                        navigationIcon = {
                            IconButton(onClick = {
                                drawerState = if (drawerState == DrawerState.CLOSED) {
                                    DrawerState.OPENED
                                } else {
                                    DrawerState.CLOSED
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                        }
                    )


                },
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        selectedIndex = selectedPage.value.ordinal,
                        onItemSelected = { index ->
                            selectedPage.value = BottomNavItem.entries.toTypedArray()[index]
                        },
                        modifier = Modifier.padding(16.dp)
                    )
                },

            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    when (selectedPage.value) {
                        BottomNavItem.Home -> HomeScreen()
                        BottomNavItem.Notice -> NoticeScreen()
                        BottomNavItem.Gallery -> GalleryScreen()
                        BottomNavItem.Faculty -> FacultyScreen()
                        BottomNavItem.About -> AboutScreen()
                    }
                }
            }
        },
        drawerContent = {
            DrawerContent(selectedPage = selectedPage.value) { page ->
                drawerState = DrawerState.CLOSED
                selectedPage.value = page
                navController.navigate(page.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}

@Composable
fun DrawerContent(selectedPage: BottomNavItem, onClick: (BottomNavItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ascol), // Replace with your logo
                contentDescription = "Logged In User Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(start = 0.dp, top = 80.dp)
            )

            // Iterate through your bottom nav items for the drawer list
            val pageList = listOf(
                BottomNavItem.Home,
                BottomNavItem.Notice,
                BottomNavItem.Gallery,
                BottomNavItem.Faculty,
                BottomNavItem.About
            )

            pageList.forEach { page ->
                DrawerListItem(page = page, isSelected = selectedPage == page) {
                    onClick(page)
                }
            }

            // Footer with logo and version information in a row
            Spacer(modifier = Modifier.weight(1f)) // Pushes the footer to the bottom
            Row(
                modifier = Modifier.padding(bottom = 16.dp), // Adjust as needed
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between logo and text
            ) {
                // Version Logo (Optional)
                Image(
                    painter = painterResource(id = R.drawable.logo), // Replace with your version logo resource
                    contentDescription = "Version Icon", // Optional icon for version
                    modifier = Modifier.size(55.dp) // Adjust size as needed
                )
                // Version Text
                Column(
                    horizontalAlignment = Alignment.Start // Align text to start
                ) {
                    Text(
                        text = "Version 1.0.0",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Made by Ishwor Khadka",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Composable
fun DrawerListItem(page: BottomNavItem, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(start = 0.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent)
            .clickableWithoutRipple { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
    ) {
        val contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        Icon(
            painter = painterResource(id = page.iconRes), // Use the icon resource from BottomNavItem
            contentDescription = null,
            tint = contentColor,
        )
        Text(text = page.label, color = contentColor, fontWeight = FontWeight.Bold)
    }
}

enum class DrawerState {
    OPENED,CLOSED
}
