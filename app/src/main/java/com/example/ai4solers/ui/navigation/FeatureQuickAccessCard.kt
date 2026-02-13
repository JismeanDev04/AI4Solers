package com.example.ai4solers.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ai4solers.ui.feature_home.CircleMenuItem

@Composable
fun FeatureQuickAccessCard(
    onNavigate: (Route) -> Unit
) {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CircleMenuItem(
                title = "Chat",
                icon = Icons.Default.Chat,
                onClick = {}
            )

            CircleMenuItem(
                title = "Create",
                icon = Icons.Default.Create,
                onClick = { onNavigate(Route.TextToImage) }
            )

            CircleMenuItem(
                title = "Remove",
                icon = Icons.Default.ContentCut,
                onClick = { onNavigate(Route.RemoveBg) }
            )

            CircleMenuItem(
                title = "Replace",
                icon = Icons.Default.Brush,
                onClick = { onNavigate(Route.ReplaceBg) }
            )
        }
    }

}