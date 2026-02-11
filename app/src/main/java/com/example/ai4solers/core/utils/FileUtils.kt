package com.example.ai4solers.core.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object FileUtils {

    //Case 1: Chup hinh tu camera: tao cache trong luu anh -> truy cap bang Uri
    //File luu anh tu camera
    fun createTempImageFile(context: Context): File {
        val fileName = "temp_image_${System.currentTimeMillis()}.jpg"
        return File(context.cacheDir, fileName)
    }

    //Lay uri cho file tam
    fun getUriForFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    //Case 2: Lay hinh tu gallery thanh File
    //Gallery chi cung cap Uri tham chieu den cloud tren google photos
    //Neu muon xai Retrofit thi phai la File: Convert Uri -> File
    //Chuyen Uri tu Gallery thanh File that de upload
    fun uriToFile(context: Context, uri: Uri): File? {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val tempFile = createTempImageFile(context)
            val outputStream = FileOutputStream(tempFile)

            inputStream?.use { input->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}