package com.example.ai4solers.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ai4solers.ui.feature_dashboard.FeatureDashboardScreen
import com.example.ai4solers.ui.feature_history.HistoryScreen
import com.example.ai4solers.ui.feature_home.HomeScreen
import com.example.ai4solers.ui.feature_tools.bg_remover.RemoveBgScreen
import com.example.ai4solers.ui.feature_tools.bg_replacer.ReplaceBgScreen
import com.example.ai4solers.ui.feature_tools.text_to_image.TextToImageScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1E1E1E),
                contentColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                mainBottomNavItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.hasRoute(item.route::class)
                    } == true

                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(item.title)
                        },
                        selected = isSelected,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color(0xFFE94057)
                        ),
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Route.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Route.Home> {
                HomeScreen(
                    onNavigateToFeature = { target ->
                        navController.navigate(target)
                    }
                )
            }

            composable<Route.Feature> {
                FeatureDashboardScreen(
                    onNavigateToTool = { routeTool ->
                        navController.navigate(routeTool)
                    }
                )
            }

            composable<Route.Model> {
                PlaceholderScreen("Đang phát triển tính năng")
            }

            composable<Route.Setting> {
                PlaceholderScreen("Đang phát triển tính năng")
            }

            composable<Route.TextToImage> { TextToImageScreen() }
            composable<Route.RemoveBg> { RemoveBgScreen() }
            composable<Route.ReplaceBg> { ReplaceBgScreen() }
            composable<Route.History> { HistoryScreen() }

        }
    }

}

@Composable
fun PlaceholderScreen(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}