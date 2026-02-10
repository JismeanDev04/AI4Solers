package com.example.ai4solers.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.data.remote.clipdrop.ClipDropApi
import com.example.ai4solers.data.remote.removebg.RemoveBgApi
import com.example.ai4solers.domain.repository.IAIProcessingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AIProcessingRepositoryImpl @Inject constructor(
    private val clipDropApi: ClipDropApi,
    private val removeBgApi: RemoveBgApi
): IAIProcessingRepository {

    //text-to-image
    override suspend fun generateTextToImage(
        prompt: String,
        apiKey: String
    ): Flow<Resource<Bitmap>> = flow {

        //emit ra Loading state cho UI
        emit(Resource.Loading())

        try {
            //bien luu du lieu gui di
            val promptBody = prompt.toRequestBody("text/plain".toMediaTypeOrNull())

            //Goi api
            val response = clipDropApi.textToImage(apiKey, promptBody)

            //Check
            if (response.isSuccessful && response.body() != null) {

                //Neu thanh cong, server tra ve du lieu anh byte stream
                //Dung bitmap Factory de render
                val inputStream = response.body()!!.byteStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)

                //Update lai Success
                emit(Resource.Success(bitmap))
            } else {
                emit(Resource.Error("Loi server: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error("Loi ket noi: ${e.localizedMessage}"))
        }

    }

    //remove-background
    override suspend fun removeBackground(
        imageFile: Bitmap,
        apiKey: String
    ): Flow<Resource<Bitmap>> {
        TODO("Not yet implemented")
    }

    //replace-background
    override suspend fun replaceBackground(
        imageFile: Bitmap,
        prompt: String,
        apiKey: String
    ): Flow<Resource<Bitmap>> {
        TODO("Not yet implemented")
    }

}