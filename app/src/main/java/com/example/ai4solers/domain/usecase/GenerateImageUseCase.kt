package com.example.ai4solers.domain.usecase

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Constants
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.repository.IAIProcessingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenerateImageUseCase @Inject constructor(
    private val repository: IAIProcessingRepository
) {
    operator fun invoke(prompt: String): Flow<Resource<Bitmap>> {

        //validate prompt
        if (prompt.isBlank()) {
            return flow { emit(Resource.Error("Prompt khong duoc de trong")) }
        }
        return runCatching {
            repository.generateTextToImage(prompt, Constants.CLIP_DROP_API_KEY)
        }.getOrElse { e ->
            flow { emit(Resource.Error("Loi: ${e.message}")) }
        }
    }
}