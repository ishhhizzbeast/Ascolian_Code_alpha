package com.example.ascolian.presentation.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ascolian.R
import com.example.ascolian.Utils.DotIndicators
import com.example.ascolian.Utils.carouselTransition
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotableAlumniComposable() {
    val images = listOf(
        R.drawable.kulmanghi,
        R.drawable.mahabir,
        R.drawable.upendradev,
        R.drawable.dayanandabajori,
        R.drawable.baburam,
        R.drawable.jhalanath,
        R.drawable.prakashman,
        R.drawable.kuberrana,
        R.drawable.pradip_paudel
    )
    val titles = listOf(
        "Managing Director, Nepal Electricity Authority",
        "Founder, Nepal Innovation Center",
        "Neurosurgeon and Founder, National Institute of Neurological and Allied Sciences",
        "Former Vice-Chancellor, Tribhuvan University",
        "Former Prime Minister of Nepal",
        "Former Prime Minister of Nepal",
        "Former Deputy Prime Minister of Nepal",
        "Former Inspector General of Police, Nepal",
        " Youth Leader, Nepali Congress"
        )
    val descriptions = listOf(
        " Known for eradicating load-shedding, Kul Man Ghising revolutionized Nepal's energy sector. His leadership brought uninterrupted electricity to millions.",
        "Mahabir Pun is a Nepali researcher, teacher, social entrepreneur and an activist known for his work in applying wireless technologies to develop remote areas of the Himalayas, also known as the Nepal Wireless Networking Project",
        "Dr. Upendra Devkota was a pioneer of neurosurgery in Nepal. He established the country’s leading neurological institute, advancing medical science.",
        "Dayananda Bajracharya was an esteemed academic leader and biologist. He played a pivotal role in shaping the future of Nepalese higher education.",
        "Baburam Bhattarai is an influential politician and intellectual. As Prime Minister, he was a key figure in Nepal's peace process and political transition.",
        "Jhala Nath Khanal is a veteran politician and leader in Nepal’s communist movement. His tenure as Prime Minister focused on political stability and unity.",
        "Prakash Man Singh is a prominent political figure in Nepal. He has played a significant role in the country’s democratic development.",
        "Kuber Rana served as Nepal's top law enforcement official. His leadership was instrumental in maintaining public safety and order.",
        "Pradip Paudel is an emerging political leader in Nepal. He advocates for youth engagement and is a strong voice for social change."
    )

    val pagerState = rememberPagerState(pageCount = { images.size })
    val autoScrollDuration = 3000L

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        // Auto-scrolling effect
        LaunchedEffect(pagerState.currentPage) {
            delay(autoScrollDuration)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(450.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VerticalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = "Ascolians Shaping the Nation",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        VerticalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.height(10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 32.dp),
                pageSpacing = 16.dp
            ) { page ->
                FlippableAlumniCard(
                    imageRes = images[page],
                    title = titles[page],
                    description = descriptions[page],
                    modifier = Modifier.carouselTransition(page,pagerState)
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            // Dot indicators
            DotIndicators(
                pageCount = images.size,
                pagerState = pagerState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun FlippableAlumniCard(imageRes: Int, title: String, description: String,modifier: Modifier) {
    val flipController = rememberFlipController()

    Flippable(
        frontSide = {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(vertical = 12.dp)
                    .clickable { flipController.flip() },
                shape = RoundedCornerShape(16.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = title,
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
                        text = title,
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
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(vertical = 12.dp)
                    .clickable { flipController.flip() },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffD7C0AE)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = description,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        flipController = flipController
    )
}
