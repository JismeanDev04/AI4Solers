package com.example.ai4solers.domain.repository

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Resource
import kotlinx.coroutines.flow.Flow

interface IAIProcessingRepository {

    //text-to-image
    suspend fun generateTextToImage(prompt: String, apiKey: String): Flow<Resource<Bitmap>>

    //remove-background
    suspend fun removeBackground(imageFile: Bitmap, apiKey: String): Flow<Resource<Bitmap>>

    //replace-background-with-prompt
    suspend fun replaceBackground(imageFile: Bitmap, prompt: String, apiKey: String): Flow<Resource<Bitmap>>

}