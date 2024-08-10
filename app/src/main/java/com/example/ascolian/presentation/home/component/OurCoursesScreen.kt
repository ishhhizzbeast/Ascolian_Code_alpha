package com.example.ascolian.presentation.home.component
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun OurCoursesScreen() {
    Box(modifier = Modifier.height(400.dp).fillMaxWidth().padding(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = "Our Courses",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
            VerticalDivider(
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(10.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.8f),
                contentAlignment = Alignment.Center
            ) {

                // List of courses
                val courses = listOf(
                    "BSC(Mathematics)" to "Bachelor of Science in Mathematics",
                    "M.Sc. Chemistry" to "M.Sc. in Chemistry",
                    "M.Sc. Botany" to "M.Sc. in Botany",
                    "M.Sc. Physics" to "M.Sc. in Physics",
                    "M.Sc. Zoology" to "M.Sc. in Zoology",
                    "MIT" to "Master in Information Technology",
                    "B.Sc.CSIT" to "Bachelor of Science in Computer Science and Information Technology",
                    "BSC(Chemistry)" to "Bachelor of Science in Chemistry",
                    "BSC(Statistics)" to "Bachelor of Science in Statistics",
                    "B.Sc. Physics" to "B.Sc. General Physical Group (4 year)",
                    "B.Sc. (Microbiology)" to "Bachelor of Science in Microbiology",
                    "BIT" to "Bachelor Degree in Information Technology"
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(courses) { course ->
                        CourseBox(shortName = course.first, fullName = course.second)
                    }
                }
            }
        }
    }
}

@Composable
fun CourseBox(shortName: String, fullName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = shortName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = fullName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
