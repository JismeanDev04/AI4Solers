package com.example.ai4solers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ai4solers.core.ui.AI4SolersTheme
import com.example.ai4solers.ui.feature_history.HistoryScreen
import com.example.ai4solers.ui.feature_tools.bg_remover.RemoveBgScreen
import com.example.ai4solers.ui.feature_tools.bg_replacer.ReplaceBgScreen
import com.example.ai4solers.ui.feature_tools.text_to_image.TextToImageScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AI4SolersTheme {
                RemoveBgScreen()
            }
        }
    }

}