package com.livingtechusa.gotjokes.ui.build

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.data.api.model.Joke
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.YoMamma
import com.livingtechusa.gotjokes.network.ImgFlipApi
import com.livingtechusa.gotjokes.network.YoMammaApi
import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val STATE_KEY_URL = "com.livingtechusa.gotjokes.ui.build.joke.url"

enum class ApiStatus { PRE_INIT, LOADING, ERROR, DONE }

class BuildViewModel() : ViewModel() {

//    private val _status = MutableStateFlow(ApiStatus.PRE_INIT)
//    val status: StateFlow<ApiStatus>
//        get() = _status

    private val _imgFlipMemeList = MutableStateFlow(emptyList<ImgFlip.Data.Meme>())
    val imgFlipMemeList: StateFlow<List<ImgFlip.Data.Meme>> get() = _imgFlipMemeList

    private val _imgFlipMeme = MutableStateFlow(ImgFlip.Data.Meme.buildFromJson(null))
    val imgFlipMeme: StateFlow<ImgFlip.Data.Meme?> get() = _imgFlipMeme

    private val _yoMamma = MutableStateFlow(YoMamma(null))
    val yoMamma: StateFlow<YoMamma> get() = _yoMamma //as StateFlow<String>


    private val _joke = MutableStateFlow(Joke())
    var joke: StateFlow<Joke> = _joke

    var _loading: Boolean by mutableStateOf(false)
    val loading: Boolean get() = _loading


    init {
        _loading = true
        getImgFlipPhotos()
        getYoMammaJokes()
//        if (state.get<String>(STATE_KEY_URL) == "com.livingtechusa.gotjokes.ui.build.joke.url") {
//            state.get<String>(STATE_KEY_URL)?.let { imgFlipUrl ->
//                joke.image = imgFlipUrl
//            } ?: onTriggerEvent(GetImgFlipImages)
//        } else {
//            onTriggerEvent(GetImgFlipImages)
//        }

    }



    /**
     * Gets  photos from the ImgFlip API Retrofit service and updates the
     * [ImageFlipPhotos] [List] [LiveData].
     */
    private fun getImgFlipPhotos() {
        viewModelScope.launch {
            val listResult = ImgFlipApi.retrofitService.getImgFlipMeme()
            _imgFlipMemeList.value = listResult.data.memes
            getImgFlipImage()
            _loading = false
        }
    }

    /**
     * Gets jokes from the YoMamma API Retrofit service and updates the
     * [YoMammaJokes] [List] [LiveData].
     */
    private fun getYoMammaJokes() {
        viewModelScope.launch {
            val result = YoMammaApi.retrofitService.getYoMammaJoke()
            _yoMamma.value = result
            _loading = false
        }
    }

    fun onTriggerEvent(event: BuildEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetImgFlipImages -> {
                        getImgFlipImageList()
                        getYoMammaJokes()
                    }
                    is GetNewImgFlipImage -> {
                        getImgFlipImage()
                        getYoMammaJokes()
                    }
                }
            } catch (e: Exception) {
                Log.e(
                    "BuildViewModel - ImgFlip: ",
                    "Exception: ${e.message}  with cause: ${e.cause}"
                )
            }
        }
    }

    private fun getImgFlipImage() {
        if (_imgFlipMemeList.value.size > 0) {
            val rand = (0.._imgFlipMemeList.value.size - 1).shuffled().last()
            val meme = _imgFlipMemeList.value[rand]
            _imgFlipMeme.value = meme
        } else {
            getImgFlipImageList()
        }
    }

    private fun getImgFlipImageList() {
        _loading = true
        getImgFlipPhotos()
        val rand = (0.._imgFlipMemeList.value.size - 1).shuffled().last()
        val meme = imgFlipMemeList.value.get(rand)
        _imgFlipMeme.value = meme
        _loading = false
    }

    //notify on user input
//    fun UiUpdatedByUser(joke: Joke, bool: Boolean) {
//        _joke.chuckNorris = "Bad Ass"
//    }

}
