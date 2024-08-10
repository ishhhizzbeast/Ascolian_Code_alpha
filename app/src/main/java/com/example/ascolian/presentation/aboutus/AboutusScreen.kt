package com.example.ascolian.presentation.aboutus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ascolian.R
import com.wajahatkarim.flippable.FlipAnimationType
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        FlippableHeaderCard(
            imageRes = R.drawable.ascolbuilding,
            frontText = "Amrit Science Campus (ASCOL)",
            backText ="Amrit Science Campus is the oldest pure science college in Nepal established in 1962.",
        )

        TextSection(
            title = "History of Amrit Campus (ASCOL)",
            content = "Amrit Science Campus (ASCOL) formerly known as Public Science College (PUSCOL) is a government institution, with partial decentralization, affiliated to Tribhuvan University and commited to conduct only science and technology programs at highest level. The Campus was established in 2013 B. S. With a view to promote the study of science and technology in Nepal by the founder Principal of Public Science College, Late Amrit Prasad Pradhan, Professor of Chemistry. Prof. Amrit Pradhan threw himself heart and soul into the development of campus. The campus has benefited greatly from his spirit of enterprise, dedication and enthusiasm. He died in an air crash over Mt. Blanc on January 24, 1966 on a mission to the United States for a cause connected with the college.\n\nAccording to data available in ministry of education, planning, statistics, and research division, singh darbar, kathmandu, nepal, 1970, p 30-31, amrit science campus (ascol) established in 1957 (2013-6-1 bs) before the establishment of tribhuvan university and completed golden jubilee in the year 2007. Presently, ascol is situated in the heart of kathmandu city (27°43'3\"n & 85°18'46\"e) at lainchaur, just100 meter far west from the popular narayanhity palace of the city.(for details please visit http://wikimapia.org/13719981/amrit-science-campus-ascol).\n\nThe contribution of Amrit campus is well recognized in the country as well as abroad. Some of the finest technical man powers, who are now, involved in different development activities both at home and abroad, were mostly students of this Campus."
        )

        FlippableHeaderCard(
            imageRes = R.drawable.amritprasadpradhan,
            frontText = "Legend Amrit Prashad Pradhan",
            backText = "Late legend Principal Amrit Prasad Pradhan established Amrit Campus with a view to promote the study of Science and Technology in Nepal."
        )

        BulletPointsSection(
            title = "Vision of the Campus",
            points = listOf(
                "To develop skilled and highly competent human resources in the field of Science and technology.",
                "To upgrade the existing infrastructure facilities for implementation of new programs.",
                "To enhance scientific research activities for human resource development.",
                "To establish research oriented education system up to Ph.D level and promote all departments to become “A Center for Excellence”."
            )
        )

        BulletPointsSection(
            title = "Mission of the Campus",
            points = listOf(
                "To introduce Master level program in the Department Microbiology and CSIT.",
                "To construct a well equipped new building as per master plan to fulfill the above vision.",
                "To create friendly teaching learning environment through healthy teacher-student interactions, seminars and group discussions.",
                "To mentor students who can think critically, communicate effectively, and academically sound for the global market.",
                "To renovate and upgrade the existing classroom and laboratory facilities with LCD projectors and internet facilities for update knowledge sharing."
            )
        )

        FooterSection()
    }
}

@Composable
fun FlippableHeaderCard(imageRes: Int, frontText: String, backText: String) {
    val flipController = rememberFlipController()
    Flippable(
        frontSide = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                onClick = { flipController.flip() }
            ) {
                Box {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Front Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 150f
                                )
                            )
                    )
                    Text(
                        text = frontText,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    )
                }
            }
        },
        backSide = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp),
                onClick = { flipController.flip() }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xffD7C0AE)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = backText,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        },
        flipController = flipController,
        flipOnTouch = true,
        flipAnimationType = FlipAnimationType.HORIZONTAL_CLOCKWISE
    )
}
