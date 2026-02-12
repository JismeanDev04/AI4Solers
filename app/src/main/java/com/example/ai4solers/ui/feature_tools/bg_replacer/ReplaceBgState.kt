package com.example.ai4solers.ui.feature_tools.bg_replacer

import android.graphics.Bitmap
import java.io.File

data class ReplaceBgState(
    val isLoading: Boolean = false,
    val originalImage: File? = null,
    val resultImage: Bitmap? = null,
    val prompt: String = "",
    val error: String? = null
)