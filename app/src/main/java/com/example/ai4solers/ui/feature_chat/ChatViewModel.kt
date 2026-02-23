package com.example.ai4solers.ui.feature_chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai4solers.core.common.Constants
import com.google.ai.client.generativeai.GenerativeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(): ViewModel() {

    //Khoi tao model gemini-2.5-flash
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = Constants.GEMINI_API_KEY
    )

    //Tao mot chat session de gemini ghi nho cuoc tro chuyen
    private val chatSession = generativeModel.startChat()

    private val _messages = MutableStateFlow<List<ChatState>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(prompt: String) {
        if (prompt.isBlank()) {
            return
        }

        //Tin nhan user
        val userMessage = ChatState(text = prompt, isUser = true)
        _messages.update { it + userMessage }

        //Sau khi send doi gemini suy nghi
        val loadingMessage = ChatState(text = "Đang suy nghĩ...", isUser = false, isLoading = true)
        _messages.update { it + loadingMessage }

        viewModelScope.launch {
            try {
                val response = chatSession.sendMessage(prompt)

                _messages.update { currentList ->
                    val newList = currentList.toMutableList()
                    newList[newList.lastIndex] = ChatState(
                        text = response.text ?: "Lỗi!",
                        isUser = false,
                        isLoading = false
                    )
                    newList
                }
            } catch (e: Exception) {
                _messages.update { currentList ->
                    val newList = currentList.toMutableList()
                    newList[newList.lastIndex] = ChatState(
                        text = "Lỗi: ${e.message}",
                        isUser = false,
                        isLoading = false
                    )
                    newList
                }
            }
        }


    }

}