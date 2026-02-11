package com.example.ai4solers.domain.repository

import com.example.ai4solers.data.local.db.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface IHistoryRepository {

    //Luu lich su
    suspend fun saveHistory(history: HistoryEntity)

    //Lay danh sach luu lich su
    fun getAllHistory(): Flow<List<HistoryEntity>>
}