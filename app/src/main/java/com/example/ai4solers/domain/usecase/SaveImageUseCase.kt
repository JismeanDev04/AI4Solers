package com.example.ai4solers.domain.usecase

import android.graphics.Bitmap
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.data.local.db.HistoryEntity
import com.example.ai4solers.data.local.storage.LocalStorageManager
import com.example.ai4solers.domain.repository.IHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val localStorageManager: LocalStorageManager,
    private val historyRepository: IHistoryRepository
) {
    operator fun invoke(bitmap: Bitmap,
                        prompt: String,
                        toolType: String
    ) : Flow<Resource<Boolean>> = flow {

        emit(Resource.Loading())

        try {

            //dat ten file
            val filename = "AI_${System.currentTimeMillis()}.jpg"

            val savedUri = localStorageManager.saveImageToGallery(
                bitmap = bitmap,
                fileName = filename
            )

            if (savedUri != null) {
                val history = HistoryEntity(
                    imagePath = savedUri,
                    prompt = prompt,
                    toolType = toolType
                )
                historyRepository.saveHistory(history)

                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Khong the luu anh vao bo nho"))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Loi khi luu: ${e.message}"))
        }

    }
}