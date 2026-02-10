package com.example.ai4solers.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//Tao mot table
@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    //Duong dan hinh anh
    val imagePath: String,

    //Prompt tao ra hinh anh
    val prompt: String,

    //Loai tool AI su dung
    val toolType: String,

    //Thoi gian tao ra hinh anh
    //Dua vao thoi gian de dua hinh anh moi nhat len dau
    val timestamp: Long = System.currentTimeMillis()
)