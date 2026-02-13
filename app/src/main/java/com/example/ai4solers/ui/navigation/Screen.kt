package com.example.ai4solers.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Home: Route

    @Serializable
    data object Feature: Route

    @Serializable
    data object Model: Route

    @Serializable
    data object Setting: Route

    @Serializable
    data object TextToImage : Route

    @Serializable
    data object RemoveBg : Route

    @Serializable
    data object ReplaceBg : Route

    @Serializable
    data object History : Route
}

data class BottomNavItem(
    val route: Route,
    val title: String,
    val icon: ImageVector
)

val mainBottomNavItems = listOf(
    BottomNavItem(Route.Home, "Home", Icons.Default.Home),
    BottomNavItem(Route.Feature, "Feature", Icons.Default.Layers),
    BottomNavItem(Route.Model, "Models", Icons.Default.ShoppingBag),
    BottomNavItem(Route.Setting, "Setting", Icons.Default.Settings)
)

val bottomNavItems = listOf(
    BottomNavItem(Route.TextToImage, "Tạo ảnh", Icons.Default.Create),
    BottomNavItem(Route.RemoveBg, "Xóa nền", Icons.Default.ContentCut),
    BottomNavItem(Route.ReplaceBg, "Thay nền", Icons.Default.Brush),
    BottomNavItem(Route.History, "Xem ảnh", Icons.Default.History)
)