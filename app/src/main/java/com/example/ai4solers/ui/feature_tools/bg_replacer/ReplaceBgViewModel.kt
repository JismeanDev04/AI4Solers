package com.example.ai4solers.ui.feature_tools.bg_replacer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.usecase.ReplaceBackgroundUseCase
import com.example.ai4solers.domain.usecase.SaveImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ReplaceBgViewModel @Inject constructor(
    private val replaceBackgroundUseCase: ReplaceBackgroundUseCase,
    private val saveImageUseCase: SaveImageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReplaceBgState())
    private val _saveState = MutableStateFlow<Resource<Boolean>?>(null)

    val uiState = _uiState.asStateFlow()
    val saveState = _saveState.asStateFlow()

    fun onImageSelected(file: File) {
        _uiState.update {
            it.copy(originalImage = file, error = null, resultImage = null)
        }
    }

    fun onPromptChange(newPrompt: String) {
        _uiState.update {
            it.copy(prompt = newPrompt)
        }
    }

    fun replaceBackground() {

        val currentState = _uiState.value

        if (currentState.originalImage == null) return

        replaceBackgroundUseCase(
            currentState.originalImage,
            _uiState.value.prompt
        ).onEach { result ->
            when (result) {
                is Resource.Loading ->
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                is Resource.Success ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            resultImage = result.data
                        )
                    }

                is Resource.Error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
            }
        }.launchIn(viewModelScope)
    }

    //Luu anh sau khi replace background
    fun saveImage() {
        val result = _uiState.value.resultImage
        val prompt = _uiState.value.prompt

        if (result != null) {
            saveImageUseCase(
                bitmap = result,
                prompt = prompt,
                toolType = "Replace Background"
            ).onEach { result ->
                _saveState.value = result
            }.launchIn(viewModelScope)
        }
    }

    fun resetSaveState() {
        _saveState.value = null
    }

}