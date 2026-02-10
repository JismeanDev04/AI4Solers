package com.example.ai4solers.data.remote.removebg

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RemoveBgApi {

    //remove background
    @Multipart
    @POST("v1.0/removebg")
    suspend fun removeBackground(
        @Header("x-api-key") apiKey: String,
        @Part image: MultipartBody.Part,
        @Part("size") size: RequestBody? = null
    ): Response<ResponseBody>
}