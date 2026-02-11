package com.example.ai4solers.ui.feature_tools.bg_remover

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.ai4solers.core.utils.FileUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoveBgScreen(
    viewModel: RemoveBgViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    //Giu Uri khi chup anh
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    //Xu ly viec chup anh, neu thanh cong thi chuyen thanh File de upload
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && tempCameraUri != null) {

                //Chup thanh cong convert Uri sang File xu ly
                val file = FileUtils.uriToFile(context, tempCameraUri!!)
                if (file != null) {
                    viewModel.onImageSelected(file)
                }
            }
        }
    )

    //Xin quyen cap phep tu nguoi dung
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                //Quyen da duoc cap phep
                val file = FileUtils.createTempImageFile(context)
                val uri = FileUtils.getUriForFile(context, file)
                tempCameraUri = uri
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Cần quyền truy cập camera", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                val file = FileUtils.uriToFile(context, uri)
                if (file != null) {
                    viewModel.onImageSelected(file)
                }
            }
        }
    )

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Xóa phông nền") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                if (state.resultImage != null) { //Lay hinh ket qua render len
                    Image(
                        bitmap = state.resultImage!!.asImageBitmap(),
                        contentDescription = null
                    )
                } else if (state.originalImage != null) { //Dung de hien thi hinh vua chon
                    Image(
                        painter = rememberAsyncImagePainter(model = state.originalImage),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text("Chưa có ảnh nào được chọn", color = Color.White)
                }

                if (state.isLoading) CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                //Nut chup anh
                Button(
                    onClick = {

                        val permissionCheckResult = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.CAMERA)

                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            val file = FileUtils.createTempImageFile(context)
                            val uri = FileUtils.getUriForFile(context, file)
                            tempCameraUri = uri
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                ) {
                    Text("📸 Camera")
                }

                //Nut lay anh tu thu vien
                Button(
                    onClick = {
                        galleryLauncher.launch(PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        ))
                    }
                ) {
                    Text("🖼️ Thư viện")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            //Nut remove background, chi hien ra khi co anh load len
            if (state.originalImage != null && !state.isLoading) {
                Button(
                    onClick = { viewModel.removeBackground() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Xóa phông nè!")
                }
            }

            if (state.error != null) {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

        }

    }

}
