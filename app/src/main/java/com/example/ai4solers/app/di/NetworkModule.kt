package com.example.ai4solers.app.di

import com.example.ai4solers.core.common.Constants
import com.example.ai4solers.data.remote.clipdrop.ClipDropApi
import com.example.ai4solers.data.remote.removebg.RemoveBgApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Create OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY //xem log request co dung ko
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    //Create Retrofit Clip_drop
    @Provides
    @Singleton
    @Named("ClipDropRetrofit")
    fun provideClipDropRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.CLIP_DROP_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Create Retrofit Remove Bg
    @Provides
    @Singleton
    @Named("RemoveBgRetrofit")
    fun provideRemoveBgRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.REMOVE_BG_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //API service
    @Provides
    @Singleton
    fun provideClipDropApi(@Named("ClipDropRetrofit") retrofit: Retrofit): ClipDropApi {
        return retrofit.create(ClipDropApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoveBgApi(@Named("RemoveBgRetrofit") retrofit: Retrofit): RemoveBgApi {
        return retrofit.create(RemoveBgApi::class.java)
    }

}
