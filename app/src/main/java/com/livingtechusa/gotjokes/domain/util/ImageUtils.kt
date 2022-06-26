package com.livingtechusa.gotjokes.domain.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.livingtechusa.gotjokes.R


const val DEFAULT_IMAGE = R.drawable.charlie_chaplin

@Composable
fun LoadPicture(
    url: String,
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?> {
    val bitmapState = remember(DEFAULT_IMAGE) {
        mutableStateOf<Bitmap?>(null)
    }
    //val bitmapState: MutableState<Bitmap?>  by remember { mutableStateOf(Bitmap(), ) }

    //show default
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(DEFAULT_IMAGE)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })

    //If image present
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                println("calling onLoad Cleared with ${placeholder} with ${placeholder?.callback}")
            }
        })
    return bitmapState
}

@Composable
fun LoadPicture(
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?> {

    val bitmapState = remember(DEFAULT_IMAGE) {
        mutableStateOf<Bitmap?>(null)
    }
    //val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    //show default while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })
    return bitmapState
}