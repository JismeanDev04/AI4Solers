package com.example.ai4solers.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    //Them hinh anh vao database
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Neu co hinh anh trung thi thay the
    suspend fun insertHistory(historyEntity: HistoryEntity)

    //Lay ra lich su database
    @Query("SELECT * FROM history_table ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<HistoryEntity>>

    //Xoa history
    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)
}