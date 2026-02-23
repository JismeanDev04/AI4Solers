package com.example.ai4solers.core.common

import com.google.ai.client.generativeai.BuildConfig

object Constants {
    //Database
    const val DATABASE_NAME = "ai4solers_db"

    //Storage
    const val APP_FOLDER_NAME = "AI4Solers_Gallery"

    //API key
    //xem trong local.properties, nham de tranh leak key
    //test
    const val CLIP_DROP_API_KEY = com.example.ai4solers.BuildConfig.CLIP_DROP_API_KEY
    const val REMOVE_BG_API_KEY = com.example.ai4solers.BuildConfig.REMOVE_BG_API_KEY
    const val GEMINI_API_KEY  = com.example.ai4solers.BuildConfig.GEMINI_API_KEY

    //base url
    const val CLIP_DROP_URL = "https://clipdrop-api.co/"
    const val REMOVE_BG_URL = "https://api.remove.bg/"
}