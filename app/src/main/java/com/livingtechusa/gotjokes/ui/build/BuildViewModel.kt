package com.livingtechusa.gotjokes.ui.build

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.data.api.model.ChuckNorris
import com.livingtechusa.gotjokes.data.api.model.Joke
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.RandomFact
import com.livingtechusa.gotjokes.data.api.model.YoMamma
import com.livingtechusa.gotjokes.network.ChuckNorrisApiService
import com.livingtechusa.gotjokes.network.GoogleImageApi
import com.livingtechusa.gotjokes.network.ImgFlipApi
import com.livingtechusa.gotjokes.network.RandomFactsApiService
import com.livingtechusa.gotjokes.network.YoMammaApi
import com.livingtechusa.gotjokes.network.YodaApiService
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
//
//    private val _memeMakerDataList = MutableStateFlow(emptyList<MemeMakerImage.Data>())
//    val memeMakerDataList: StateFlow<List<MemeMakerImage.Data>> get() = _memeMakerDataList

    private val _imageList = MutableStateFlow(emptyList<String>())
    val imageList: StateFlow<List<String>> get() = _imageList

    private val _imageUrl = MutableStateFlow(String())
    val imageUrl: StateFlow<String?> get() = _imageUrl


    private val _caption = MutableStateFlow(String())
    val caption: StateFlow<String> get() = _caption

    private val _yodaSpeak = MutableStateFlow(String())
    val yodaSpeak: StateFlow<String> get() = _yodaSpeak

    private val _yoMamma = MutableStateFlow(YoMamma(null))
    val yoMamma: StateFlow<YoMamma> get() = _yoMamma

    private val _randomFact = MutableStateFlow(RandomFact())
    val randomFact: StateFlow<RandomFact> get() = _randomFact

    private val _chuckNorrisJoke = MutableStateFlow(ChuckNorris())
    val chuckNorrisJoke: StateFlow<ChuckNorris> get() = _chuckNorrisJoke



    private val _joke = MutableStateFlow(Joke())
    var joke: StateFlow<Joke> = _joke

    var _loading: Boolean by mutableStateOf(false)
    val loading: Boolean get() = _loading


    init {
        _loading = true
        getImages()
        getYoMammaJokes()
        getRandomFacts()
        getChuckNorrisJokes()
        //        if (state.get<String>(STATE_KEY_URL) == "com.livingtechusa.gotjokes.ui.build.joke.url") {
        //            state.get<String>(STATE_KEY_URL)?.let { imgFlipUrl ->
        //                joke.image = imgFlipUrl
        //            } ?: onTriggerEvent(GetImgFlipImages)
        //        } else {
        //            onTriggerEvent(GetImgFlipImages)
        //        }

    }


    fun onTriggerEvent(event: BuildEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetImgFlipImages -> {
                        _caption.value = ""
                        getImageList()
                        getYoMammaJokes()
                        getRandomFacts()
                        getChuckNorrisJokes()
                    }
                    is GetNewImgFlipImage -> {
                        _caption.value = ""
                        getImage()
                        getYoMammaJokes()
                        getRandomFacts()
                        getChuckNorrisJokes()
                    }
                    is ConvertToYodaSpeak -> {
                        ConvertToTextToYodaSpeak(event.text)
                    }
                    is UpdateCaption -> {
                        _caption.value = event.text
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

    /**
     * Gets  photos from the ImgFlip API Retrofit service and updates the
     * [ImageFlipPhotos] [List] [LiveData].
     */
    private fun getImages() {
        viewModelScope.launch {
            val images = mutableListOf<String>()
            val imgFlipResult = ImgFlipApi.retrofitService.getImgFlipMeme()
            for(url in imgFlipResult.data.memes){
                images.add(url.url)
            }
            val googleImageResult = GoogleImageApi.retrofitService.getGoogleImages()
            for (item in googleImageResult.items    ){
                if(item.pagemap.imageobject?.listIterator() != null) {
                    for (tag in item.pagemap.imageobject.listIterator()) {
                        if(!tag.url.contains("logo")) {
                            images.add(tag.url)
                        }
                    }
                }
            }
            _imageList.value = images
            getImage()
            _loading = false
        }
    }

    private fun getImage() {
        if (_imageList.value.size > 0) {
            val rand = (0.._imageList.value.size - 1).shuffled().first()
            val imageUrl = imageList.value[rand]
            _imageUrl.value = imageUrl
        } else {
            getImageList()
        }
    }

    private fun getImageList() {
        _loading = true
        getImages()
        val rand = (0.._imageList.value.size - 1).shuffled().first()
        val imageURL = imageList.value.get(rand)
        _imageUrl.value = imageURL
        _loading = false
    }

    /**
     * Gets jokes from the YoMamma API Retrofit service and updates the
     * [YoMammaJokes] [List] [LiveData].
     */
    private fun getYoMammaJokes() {
        viewModelScope.launch {
            try {
                val result = YoMammaApi.retrofitService.getYoMammaJoke()
                _yoMamma.value = result
            } catch(e: Exception){
                Log.i("YoMamma", e.message + " with cause " +e.cause)
            }
            _loading = false
        }
    }

    private fun ConvertToTextToYodaSpeak(text: String) {
        viewModelScope.launch {
            try {
                val result = YodaApiService.YodaSpeakApi.retrofitService.getYodaSpeak(text)
                _caption.value = result?.contents?.translated ?: "Too many tries."
            } catch (e: Exception) {
                Log.i("Yoda", e.message + " with cause " + e.cause)
                _caption.value = "Sorry, Yoda only speaks 5 times an hour."
            }
            _loading = false
        }
    }

    private fun getRandomFacts() {
        viewModelScope.launch {
            try {
                val result = RandomFactsApiService.RandomFactsApi.retrofitService.getRandomFacts()
                if (result != null) {
                _randomFact.value = result
                }
            } catch (e: Exception) {
                Log.i("RandomFact", e.message + " with cause " + e.cause)
                _randomFact.value = RandomFact()
            }
            _loading = false
        }
    }

    private fun getChuckNorrisJokes() {
        viewModelScope.launch {
            try {
                val result = ChuckNorrisApiService.ChuckNorrisApi.retrofitService.getChuckNorrisJoke()
                if (result != null) {
                    _chuckNorrisJoke.value = result
                }
            } catch (e: Exception) {
                Log.i("RandomFact", e.message + " with cause " + e.cause)
                _chuckNorrisJoke.value = ChuckNorris()
            }
            _loading = false
        }
    }

}
