package com.example.ai4solers.domain.repository

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IAIProcessingRepository {

    //text-to-image
    fun generateTextToImage(prompt: String, apiKey: String): Flow<Resource<Bitmap>>

    //remove-background
    fun removeBackground(imageFile: File, apiKey: String): Flow<Resource<Bitmap>>

    //replace-background-with-prompt
    fun replaceBackground(imageFile: File, prompt: String, apiKey: String): Flow<Resource<Bitmap>>

}