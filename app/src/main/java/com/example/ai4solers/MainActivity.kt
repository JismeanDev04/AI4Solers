package com.example.ai4solers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ai4solers.core.ui.AI4SolersTheme
import com.example.ai4solers.ui.feature_history.HistoryScreen
import com.example.ai4solers.ui.feature_tools.bg_remover.RemoveBgScreen
import com.example.ai4solers.ui.feature_tools.bg_replacer.ReplaceBgScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AI4SolersTheme {
                HistoryScreen()
            }
        }
    }

}