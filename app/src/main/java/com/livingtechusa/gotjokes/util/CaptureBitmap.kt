package com.livingtechusa.gotjokes.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

//
//val REQUEST_IMAGE_CAPTURE = 1
//
//private fun dispatchTakePictureIntent() {
//    val getContent = registerForActivityResult(GetContent()) { uri: Uri? ->
//        // Handle the returned Uri
//    }
//
//
//    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//    try {
//        registerForActivityResult()
//
//
//        registerForActivityResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//    } catch (e: ActivityNotFoundException) {
//        // display error state to the user
//    }
//}


@Composable
fun CaptureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {

    val context = LocalContext.current

    /**
     * ComposeView that would take composable as its content
     * Kept in remember so recomposition doesn't re-initialize it
     **/
    val composeView = remember { ComposeView(context) }

    /**
     * Callback function which could get latest image bitmap
     **/
    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    /** Use Native View inside Composable **/
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )

    /** returning callback to bitmap **/
    return ::captureBitmap
}