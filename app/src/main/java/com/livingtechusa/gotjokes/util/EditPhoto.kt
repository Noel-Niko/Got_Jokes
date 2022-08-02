package com.livingtechusa.gotjokes.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.compose.rememberImagePainter


@Composable
fun EditPhoto(): Uri? {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    return imageUri
//    Column {
//        Button(onClick = { launcher.launch("image/*") }) {
//            Text(text = "Load Image")
//        }
//        Image(
//            painter = rememberImagePainter(imageUri),
//            contentDescription = "My Image"
//        )
//    }
}