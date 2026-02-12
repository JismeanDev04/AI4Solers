package com.example.ai4solers.domain.usecase

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Constants
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.repository.IAIProcessingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class ReplaceBackgroundUseCase @Inject constructor(
    private val repository: IAIProcessingRepository
) {
    operator fun invoke(imageFile: File, prompt: String): Flow<Resource<Bitmap>> {
        if (prompt.isBlank()) {
            return flow { emit(Resource.Error("Prompt không được để null")) }
        }
        return runCatching {
            repository.replaceBackground(imageFile, prompt, Constants.CLIP_DROP_API_KEY)
        }.getOrElse { e->
            flow { emit(Resource.Error("Lỗi: ${e.message}")) }
        }
    }
}