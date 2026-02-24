package com.example.ai4solers.ui.feature_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai4solers.R
import com.example.ai4solers.ui.navigation.FeatureQuickAccessCard
import com.example.ai4solers.ui.navigation.Route


data class CategoryItem(
    val title: String,
    val subtitle: String,
    val prompt: String,
    val color: Color
)
@Composable
fun HomeScreen(
    onNavigateToFeature: (Route) -> Unit
) {

    val categories = listOf(
        CategoryItem(
            title = "Luyện Nói Tiếng Anh",
            subtitle = "Tình huống giao tiếp",
            prompt = "A highly detailed, realistic image of a busy coffee shop with people talking and drinking coffee, 4k resolution, perfect for practicing english describing a scene",
            color = Color(0xFF4A3B3B)
        ),
        CategoryItem(
            title = "Minh Họa Truyện",
            subtitle = "Dự án văn học",
            prompt = "Cinderella running away from the grand ball, leaving a glass slipper on the stairs, magical glowing atmosphere, disney animation style, highly detailed",
            color = Color(0xFF3B4A48)
        ),
        CategoryItem(
            title = "Sản Phẩm",
            subtitle = "Marketing",
            prompt = "A sleek perfume bottle on a modern wooden table with soft sunlight and green plants in the blurred background, product photography, 8k",
            color = Color(0xFF4A423B)
        ),
        CategoryItem(
            title = "Cyberpunk Avatar",
            subtitle = "Ảnh đại diện",
            prompt = "A cyberpunk character portrait in a neon-lit futuristic city, highly detailed face, cinematic lighting, digital art",
            color = Color(0xFF3B3B4A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF2E1A47), Color(0xFF1A1A2E))
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(0.6f)
                ) {
                    Text(
                        "Trò chuyện với Gemini",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Cùng trải nghiệm với mô hình mới nhất từ Google",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onNavigateToFeature(Route.Chat) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE94057)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Explore Now")
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .height(150.dp)
                        .width(135.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray)
                ) {
                    Image(
                        painter = painterResource(R.drawable.go_min_si),
                        contentDescription = "my_crush",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        FeatureQuickAccessCard(onNavigate = onNavigateToFeature)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Categories",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "See all > ",
                color = Color(0xFFE94057),
                fontSize = 14.sp
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    item = category,
                    onClick = {
                        onNavigateToFeature(Route.TextToImage(prefillPrompt = category.prompt))
                    }
                )
            }
        }

    }

}

@Composable
fun CircleMenuItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF333333)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            title,
            color = Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CategoryCard(item: CategoryItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(200.dp)
            .height(160.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(item.color)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = item.subtitle,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}