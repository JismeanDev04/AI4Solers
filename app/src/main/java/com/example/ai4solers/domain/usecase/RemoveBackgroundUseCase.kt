package com.example.ai4solers.domain.usecase

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Constants
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.repository.IAIProcessingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class RemoveBackgroundUseCase @Inject constructor(
    private val repository: IAIProcessingRepository
) {
    operator fun invoke(imageFile: File): Flow<Resource<Bitmap>> {
        return repository.removeBackground(imageFile, Constants.REMOVE_BG_API_KEY)
    }
}