package com.example.ai4solers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ai4solers.core.ui.AI4SolersTheme
import com.example.ai4solers.ui.feature_tools.text_to_image.TextToImageScreen
import com.example.ai4solers.ui.navigation.MainScreen
import com.example.ai4solers.ui.navigation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AI4SolersTheme {
                MainScreen()
            }
        }
    }

}