package com.example.ai4solers.ui.feature_tools.bg_remover

import android.graphics.Bitmap
import java.io.File

data class RemoveBgState(
    val isLoading: Boolean = false,
    val originalImage: File? = null,
    val resultImage: Bitmap? = null,
    val error: String? = null
)