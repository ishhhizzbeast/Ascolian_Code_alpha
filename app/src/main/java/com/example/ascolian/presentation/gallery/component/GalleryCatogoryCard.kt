package com.example.ascolian.presentation.gallery.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ascolian.Utils.startOffsetForPage
import com.example.ascolian.domain.GalleryImage

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryCategoryCard(category: String, images: List<GalleryImage>) {
    val horizontalState = rememberPagerState(pageCount = { images.size })
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp),verticalArrangement = Arrangement.Center) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(all = 16.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .weight(.7f)
                        .padding(
                            top = 32.dp
                        ),
                    state = horizontalState,
                    pageSpacing = 1.dp,
                    beyondBoundsPageCount = 9,
                ) { page ->
                    Box(
                        modifier = Modifier
                            .zIndex(page * 10f)
                            .padding(
                                start = 64.dp,
                                end = 32.dp,
                            )
                            .graphicsLayer {
                                val startOffset = horizontalState.startOffsetForPage(page)
                                translationX = size.width * (startOffset * .99f)

                                alpha = (2f - startOffset) / 2f

                                val blur = (startOffset * 20f).coerceAtLeast(0.1f)
                                renderEffect = android.graphics.RenderEffect
                                    .createBlurEffect(
                                        blur, blur, android.graphics.Shader.TileMode.DECAL
                                    )
                                    .asComposeRenderEffect()

                                val scale = 1f - (startOffset * .1f)
                                scaleX = scale
                                scaleY = scale
                            }
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                color = Color(0xFFF58133),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = images[page].imageUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
        Spacer(modifier = Modifier.height(9.dp))

            }
        }
