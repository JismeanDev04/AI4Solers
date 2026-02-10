package com.example.ai4solers.data.remote.clipdrop

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClipDropApi {

    //Text-to-image
    @Multipart
    @POST("text-to-image/v1")
    suspend fun textToImage(
        @Header("x-api-key") apiKey: String,
        @Part("prompt") prompt: RequestBody
    ): Response<ResponseBody>

    //replace-background
    @Multipart
    @POST("replace-background/v1")
    suspend fun replaceBackground(
        @Header("x-api-key") apiKey: String,
        @Part image: MultipartBody.Part,
        @Part("prompt") prompt: RequestBody
    ): Response<ResponseBody>

}