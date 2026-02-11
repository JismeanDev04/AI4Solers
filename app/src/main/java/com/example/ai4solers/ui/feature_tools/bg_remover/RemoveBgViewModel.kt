package com.example.ai4solers.ui.feature_tools.bg_remover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.domain.usecase.RemoveBackgroundUseCase
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
class RemoveBgViewModel @Inject constructor(
    private val removeBackgroundUseCase: RemoveBackgroundUseCase,
    private val saveImageUseCase: SaveImageUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RemoveBgState())
    private val _saveState = MutableStateFlow<Resource<Boolean>?>(null)

    val uiState = _uiState.asStateFlow()
    val saveState = _saveState.asStateFlow()

    fun onImageSelected(file: File) {
        _uiState.update {
            it.copy(originalImage = file, error = null, resultImage = null)
        }
    }

    fun removeBackground() {
        val file = _uiState.value.originalImage ?: return

        removeBackgroundUseCase(file).onEach { result ->
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


}