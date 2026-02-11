package com.example.ai4solers.data.repository

import com.example.ai4solers.data.local.db.HistoryDao
import com.example.ai4solers.data.local.db.HistoryEntity
import com.example.ai4solers.domain.repository.IHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dao: HistoryDao
): IHistoryRepository {

    override suspend fun saveHistory(history: HistoryEntity) {
        dao.insertHistory(history)
    }

    override fun getAllHistory(): Flow<List<HistoryEntity>> {
        return dao.getAllHistory()
    }

}