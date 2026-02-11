package com.example.ai4solers.ui.feature_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai4solers.data.local.db.HistoryEntity
import com.example.ai4solers.domain.repository.IHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    repository: IHistoryRepository
): ViewModel() {

    val historyList: StateFlow<List<HistoryEntity>> = repository.getAllHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
    )
}