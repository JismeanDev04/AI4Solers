package com.example.ai4solers.data.local.storage

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.example.ai4solers.core.common.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStream
import javax.inject.Inject

class LocalStorageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    //Ham luu Bitmap xuong local storage
    suspend fun saveImageToGallery(bitmap: Bitmap, fileName: String): String? {

        return withContext(Dispatchers.IO) {
            try {
                val contentValue = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")

                    //Android 10 tro len phai chi dinh duong dan tuong doi
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        put(MediaStore.MediaColumns.RELATIVE_PATH,
                            "${Environment.DIRECTORY_PICTURES}/${Constants.APP_FOLDER_NAME}")
                        put(MediaStore.Images.Media.IS_PENDING, 1)
                    }
                }

                //Luu uri
                val resolver = context.contentResolver
                val uri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValue)

                //Mo output stream de ghi du lieu vao URI do
                uri?.let { imageUri ->
                    val stream: OutputStream? = resolver.openOutputStream(imageUri)

                    stream?.use { output ->
                        //Nen bitmap thanh JPEG
                        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)) {
                            throw Exception("Bitmap compression failed")
                        }
                    }

                    //Ghi xong thi cho pending = 0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        contentValue.clear()
                        contentValue.put(MediaStore.Images.Media.IS_PENDING, 0)
                        resolver.update(imageUri, contentValue, null, null)
                    }

                    //Tra ve duong dan dang chuoi
                    return@withContext imageUri.toString()
                }
                //Neu khong tao duoc uri thi tra ve null
                return@withContext null
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }

    }

}