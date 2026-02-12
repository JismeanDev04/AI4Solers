package com.example.ai4solers.ui.feature_dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai4solers.ui.navigation.Route

data class FeatureItem(
    val title: String,
    val icon: ImageVector,
    val route: Route,
    val color: Color
)

@Composable
fun FeatureDashboardScreen(
    onNavigateToTool: (Route) -> Unit
) {
    val features = listOf(
        FeatureItem("Tạo ảnh AI", Icons.Default.Create, Route.TextToImage, Color(0xFF6C63FF)),
        FeatureItem("Xóa phông", Icons.Default.ContentCut, Route.RemoveBg, Color(0xFFFF6584)),
        FeatureItem("Thay nền", Icons.Default.Brush, Route.ReplaceBg, Color(0xFFFFC048)),
        FeatureItem("Lịch sử", Icons.Default.History, Route.History, Color(0xFF00D2D3)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(12.dp)
    ) {
        Text(
            "AI tools",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            "Chọn công cụ để bắt đầu",
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(features) { item ->
                FeatureCard(item, onNavigateToTool)
            }
        }
    }
}

@Composable
fun FeatureCard(
    item: FeatureItem,
    onClick: (Route) -> Unit
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .clickable { onClick(item.route) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252525)),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(item.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    item.icon,
                    contentDescription = null,
                    tint = item.color,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                item.title,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}