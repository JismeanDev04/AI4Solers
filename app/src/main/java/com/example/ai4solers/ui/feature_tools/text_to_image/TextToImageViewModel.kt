package com.example.ai4solers.ui.feature_tools.text_to_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.usecase.GenerateImageUseCase
import com.example.ai4solers.domain.usecase.SaveImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TextToImageViewModel @Inject constructor(
    private val generateImageUseCase: GenerateImageUseCase,
    private val saveImageUseCase: SaveImageUseCase
): ViewModel() {

    //Init state
    private val _uiState = MutableStateFlow(TextToImageState())
    private val _saveState = MutableStateFlow<Resource<Boolean>?>(null)
    val uiState: StateFlow<TextToImageState> = _uiState.asStateFlow()
    val saveState = _saveState.asStateFlow()

    //Cap nhat prompt khi nguoi dung nhap
    fun onPromptChange(newPrompt: String) {
        _uiState.update { it.copy(prompt = newPrompt) }
    }

    fun generateImage() {

        //Lay prompt moi nhat tu nguoi dung
        val currentPrompt = _uiState.value.prompt

        generateImageUseCase(currentPrompt).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            resultImage = result.data, //Lay bitmap cap nhat state
                            error = null
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "Da co loi"
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveImage() {

        val image = _uiState.value.resultImage
        val prompt = _uiState.value.prompt

        if (image != null) {
            saveImageUseCase(
                bitmap = image,
                prompt = prompt,
                toolType = "Text-to-image"
            ).onEach { result ->
                _saveState.value = result
            }.launchIn(viewModelScope)
        }
    }

    fun resetSaveState() {
        _saveState.value = null
    }

}