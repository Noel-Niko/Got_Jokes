//package com.livingtechusa.gotjokes.util
//
//import android.app.Activity
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.Rect
//import android.os.Build
//import android.os.Handler
//import android.os.Looper
//import android.view.PixelCopy
//import android.view.View
//import android.view.Window
//
//
//// start of extension.
//fun View.toBitmap(onBitmapReady: (Bitmap) -> Unit, onBitmapError: (Exception) -> Unit) {
//
//    try {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            val temporalBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
//
//            // Above Android O, use PixelCopy due
//            // https://stackoverflow.com/questions/58314397/
//            val window: Window = (this.context as Activity).window
//
//            val location = IntArray(VIEW_LOCATION_SIZE)
//
//            this.getLocationInWindow(location)
//
//            val viewRectangle = Rect(location[0], location[1], location[0] + this.width, location[1] + this.height)
//
//            val onPixelCopyListener: PixelCopy.OnPixelCopyFinishedListener = PixelCopy.OnPixelCopyFinishedListener { copyResult ->
//
//                if (copyResult == PixelCopy.SUCCESS) {
//
//                    onBitmapReady(temporalBitmap)
//                } else {
//
//                    error("Error while copying pixels, copy result: $copyResult")
//                }
//            }
//
//            PixelCopy.request(window, viewRectangle, temporalBitmap, onPixelCopyListener, Handler(Looper.getMainLooper()))
//        } else {
//
//            val temporalBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565)
//
//            val canvas = android.graphics.Canvas(temporalBitmap)
//
//            this.draw(canvas)
//
//            canvas.setBitmap(null)
//
//            onBitmapReady(temporalBitmap)
//        }
//
//    } catch (exception: Exception) {
//
//        onBitmapError(exception)
//    }
//}
//
//fun captureView(view: View, window: Window, bitmapCallback: (Bitmap)->Unit) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        // Above Android O, use PixelCopy
//        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        val location = IntArray(2)
//        view.getLocationInWindow(location)
//        PixelCopy.request(window,
//                          Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
//                          bitmap,
//                          {
//                              if (it == PixelCopy.SUCCESS) {
//                                  bitmapCallback.invoke(bitmap)
//                              }
//                          },
//                          Handler(Looper.getMainLooper()) )
//    } else {
//        val tBitmap = Bitmap.createBitmap(
//            view.width, view.height, Bitmap.Config.RGB_565
//        )
//        val canvas = Canvas(tBitmap)
//        view.draw(canvas)
//        canvas.setBitmap(null)
//        bitmapCallback.invoke(tBitmap)
//    }
//}