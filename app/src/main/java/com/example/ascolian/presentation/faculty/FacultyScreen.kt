package com.example.ascolian.presentation.faculty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ascolian.R
import com.example.ascolian.Utils.PacmanIndicator
import com.example.ascolian.presentation.faculty.component.TeacherCard
import com.example.ascolian.presentation.viewmodel.GetTeacherViewModel
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun FacultyScreen() {
    val viewModel: GetTeacherViewModel = hiltViewModel()
    val teacherByDepartment = viewModel.teachersByDepartment.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    Box(modifier = Modifier.fillMaxSize().background(Color(0xffF9F7F7))) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                val departments = listOf(
                    "Botany", "Chemistry", "Computer", "Environment", "Physics",
                    "Zoology", "Microbiology", "Mathematics"
                )

                departments.forEach { department ->
                    item {
                        FlippableDepartmentCard(department)
                    }

                    val teachers = teacherByDepartment.value[department] ?: emptyList()
                    items(teachers) { teacher ->
                        TeacherCard(teacher = teacher)
                    }
                }
            }
        }

        if (isLoading) {
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
        }
    }

@Composable
fun FlippableDepartmentCard(department: String) {
    val flipController = rememberFlipController()
    val imageRes = when (department) {
        "Botany" -> R.drawable.botany
        "Chemistry" -> R.drawable.chemistry
        "Computer" -> R.drawable.computer
        "Environment" -> R.drawable.environment
        "Physics" -> R.drawable.physics
        "Zoology" -> R.drawable.zoology
        "Microbiology" -> R.drawable.microbiology
        "Mathematics" -> R.drawable.mathematics
        else -> R.drawable.computer
    }
    val departmentDefinitions = mapOf(
        "Botany" to "Botany, also called plant science, plant biology or phytology, is the science of plant life and a branch of biology.",
        "Chemistry" to "Chemistry is the scientific study of the properties and behavior of matter.",
        "Computer" to "Fundamental areas of computer science Computer science is the study of computation, information, and automation.",
        "Environment" to "Environmental science is an interdisciplinary academic field that integrates physical, biological and information sciences to study the environment.",
        "Physics" to "Physics is the natural science that studies matter, its motion and behavior through space and time, and the related entities of energy and force.",
        "Zoology" to "Zoology is the branch of biology that studies the animal kingdom, including the structure, embryology, evolution of both living and extinct.",
        "Microbiology" to "Microbiology is the study of microscopic organisms, such as bacteria, viruses, archaea, fungi and protozoa.",
        "Mathematics" to "Mathematics is an area of knowledge that includes such topics as numbers, formulas and related structures, shapes."
    )

    Flippable(
        frontSide = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(vertical = 12.dp)
                ,
                onClick = { flipController.flip() }
            ) {
                Box {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = department,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 160f
                                )
                            )
                    )
                    Text(
                        text = department,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                    )
                }
            }
        },
        backSide = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                onClick = { flipController.flip() }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xffD7C0AE))
                ) {
                    Text(
                        text = departmentDefinitions[department] ?: "",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
            }
        },
        flipController = flipController
    )
}

