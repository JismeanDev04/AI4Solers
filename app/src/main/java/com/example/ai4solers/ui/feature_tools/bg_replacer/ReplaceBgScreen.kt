package com.example.ai4solers.ui.feature_tools.bg_replacer

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.ai4solers.core.common.Resource
import com.example.ai4solers.core.utils.FileUtils
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplaceBgScreen(
    viewModel: ReplaceBgViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val saveState by viewModel.saveState.collectAsState()
    val context = LocalContext.current

    var tempCameraFile by remember { mutableStateOf<File?>(null) }


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && tempCameraFile != null) {
                viewModel.onImageSelected(tempCameraFile!!)
            }
        }
    )

    //Request permission tranh viec crash app do gap Exception
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                val file = FileUtils.createTempImageFile(context)
                tempCameraFile = file
                val uri = FileUtils.getUriForFile(context, file)
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Cần quyền truy cập camera", Toast.LENGTH_SHORT).show()
            }
        }
    )

    //Chon anh tu thu vien
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
        topBar = { CenterAlignedTopAppBar(
            title = { Text("Thay phông nền với AI") }
        ) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                if (state.resultImage != null) {
                    Image(
                        bitmap = state.resultImage!!.asImageBitmap(),
                        contentDescription = null
                    )
                } else if (state.originalImage != null) {
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

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                Button(
                    onClick = {

                        val permissionResult = ContextCompat.checkSelfPermission(context,
                            Manifest.permission.CAMERA)

                        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                            val file = FileUtils.createTempImageFile(context)
                            tempCameraFile = file
                            val uri = FileUtils.getUriForFile(context, file)
                            cameraLauncher.launch(uri)
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                ) {
                    Text("📸 Chụp mới")
                }

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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.prompt,
                onValueChange = { viewModel.onPromptChange(it)},
                label = { Text("Nhập mô tả...")},
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.replaceBackground()
                },
                enabled = !state.isLoading && state.originalImage != null && state.prompt.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Thay phông nền")
            }

            //Nut luu hinh anh
            if (state.resultImage != null) {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.saveImage()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Lưu ảnh vào thư viện")
                }
            }

            if (state.error != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

        }

    }

    LaunchedEffect(saveState) {
        when (saveState) {
            is Resource.Loading -> {
                Toast.makeText(context, "Đang lưu ảnh...", Toast.LENGTH_SHORT).show()
                viewModel.resetSaveState()
            }
            is Resource.Error -> {
                Toast.makeText(context, saveState?.message, Toast.LENGTH_SHORT).show()
                viewModel.resetSaveState()
            }
            else -> {}
        }
    }

}