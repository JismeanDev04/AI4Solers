package com.example.ai4solers.ui.feature_chat

data class ChatState (
    val text: String,
    val isUser: Boolean,
    val isLoading: Boolean = false
)