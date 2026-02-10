package com.example.ai4solers.ui.feature_tools.text_to_image

import android.graphics.Bitmap

data class TextToImageState(
    val isLoading: Boolean = false,
    val resultImage: Bitmap? = null,
    val error: String? = null,
    val prompt: String = ""
)