package com.livingtechusa.gotjokes.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient


// add a Facebook StethoInterceptor to the OkHttpClient's list of network interceptors
var okClient: OkHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(StethoInterceptor())
    .build()

