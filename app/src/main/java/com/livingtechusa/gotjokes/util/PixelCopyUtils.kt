//package com.livingtechusa.gotjokes.util
//import android.graphics.Bitmap
//import android.graphics.Rect
//import android.os.Handler
//import android.os.HandlerThread
//import android.view.PixelCopy
//import android.view.SurfaceView
//import android.view.View
//import android.view.Window
//
//object PixelCopyUtils {
//
//    fun getViewBitmap(view: View, window: Window, listener: PixelCopyListener) {
//        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        val locationOfViewInWindow = IntArray(2)
//        view.getLocationInWindow(locationOfViewInWindow)
//
//        val xCoordinate = locationOfViewInWindow[0]
//        val yCoordinate = locationOfViewInWindow[1]
//
//        val scope = Rect(
//            xCoordinate,
//            yCoordinate,
//            xCoordinate + view.width,
//            yCoordinate + view.height // shouldn't it be y MINUS HEIGHT?
//        )
//
//        val handlerThread = HandlerThread(PixelCopyHelper::class.java.simpleName)
//        handlerThread.start()
//
//        PixelCopy.request(window, scope, bitmap, { copyResult ->
//            if (copyResult == PixelCopy.SUCCESS) {
//                listener.onCopySuccess(bitmap)
//            } else {
//                listener.onCopyError()
//            }
//            handlerThread.quitSafely()
//        }, Handler(handlerThread.looper)
//        )
//    }
//
//    interface PixelCopyListener {
//        fun onCopySuccess(bitmap: Bitmap)
//        fun onCopyError()
//    }
//}