package com.livingtechusa.gotjokes.ui.build

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.data.api.ApiConstants
import com.livingtechusa.gotjokes.data.api.ApiConstants.PEXEL_API_KEY
import com.livingtechusa.gotjokes.data.api.model.Advice
import com.livingtechusa.gotjokes.data.api.model.CatFact
import com.livingtechusa.gotjokes.data.api.model.DadJokes
import com.livingtechusa.gotjokes.data.api.model.DogFact
import com.livingtechusa.gotjokes.data.api.model.ImgFlip
import com.livingtechusa.gotjokes.data.api.model.JokeApi
import com.livingtechusa.gotjokes.data.api.model.RandomFact
import com.livingtechusa.gotjokes.data.api.model.YoMamma
import com.livingtechusa.gotjokes.data.database.convertLocalDateTimeToDate
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import com.livingtechusa.gotjokes.data.database.localService.LocalServiceProvider
import com.livingtechusa.gotjokes.network.JokeApiService
import com.livingtechusa.gotjokes.network.GoogleImageApi
import com.livingtechusa.gotjokes.network.ImgFlipApi
import com.livingtechusa.gotjokes.network.RandomFactsApiService
import com.livingtechusa.gotjokes.network.YoMammaApi
import com.livingtechusa.gotjokes.network.YodaApiService
import com.livingtechusa.gotjokes.network.AdviceApiService
import com.livingtechusa.gotjokes.network.CatFactApiService
import com.livingtechusa.gotjokes.network.DadJokeApiService
import com.livingtechusa.gotjokes.network.DogFactApiService
import com.livingtechusa.gotjokes.network.PexelApi
import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

const val STATE_KEY_URL = "com.livingtechusa.gotjokes.ui.build.joke.url"

enum class ApiStatus { PRE_INIT, LOADING, ERROR, DONE }

@HiltViewModel
class BuildViewModel @Inject constructor(
    private val localService: LocalServiceProvider
) : ViewModel() {

    //    private val _status = MutableStateFlow(ApiStatus.PRE_INIT)
    //    val status: StateFlow<ApiStatus>
    //        get() = _status

    private val _imageList = MutableStateFlow(emptyList<ImageSearchEntity>())
    val imageList: StateFlow<List<ImageSearchEntity>> get() = _imageList

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

    private val _jokeApiJoke = MutableStateFlow(JokeApi())
    val jokeApiJoke: StateFlow<JokeApi> get() = _jokeApiJoke

    private val _advice = MutableStateFlow(Advice())
    val advice: StateFlow<Advice> get() = _advice

    private val _dadJoke = MutableStateFlow(DadJokes())
    val dadJoke: StateFlow<DadJokes> get() = _dadJoke

    private val _catFact = MutableStateFlow(CatFact())
    val catFact: StateFlow<CatFact> get() = _catFact

    private val _dogFact = MutableStateFlow(DogFact())
    val dogFact: StateFlow<DogFact> get() = _dogFact

    //    private val _joke = MutableStateFlow(Joke())
    //    var joke: StateFlow<Joke> = _joke

    var _loading: Boolean by mutableStateOf(false)
    val loading: Boolean get() = _loading


    init {
        _loading = true
        getImages()
        getYoMammaJokes()
        getRandomFacts()
        getjokeApiJoke()
        getAdvice()
        getDadJoke()
        getCatFact()
        getDogFact()

        //        if (state.get<String>(STATE_KEY_URL) == "com.livingtechusa.gotjokes.ui.build.joke.url") {
        //            state.get<String>(STATE_KEY_URL)?.let { imgFlipUrl ->
        //                joke.image = imgFlipUrl
        //            } ?: onTriggerEvent(GetImages)
        //        } else {
        //            onTriggerEvent(GetImages)
        //        }

    }


    fun onTriggerEvent(event: BuildEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetImages -> {
                        _caption.value = ""
                        getImageList()
                        getYoMammaJokes()
                        getRandomFacts()
                        getjokeApiJoke()
                        getAdvice()
                        getDadJoke()
                        getCatFact()
                        getDogFact()
                    }
                    is GetNewImage -> {
                        _caption.value = ""
                        getImage()
                        getYoMammaJokes()
                        getRandomFacts()
                        getjokeApiJoke()
                        getAdvice()
                        getDadJoke()
                        getCatFact()
                        getDogFact()
                    }
                    is ConvertToYodaSpeak -> {
                        ConvertToTextToYodaSpeak(event.text)
                    }
                    is UpdateCaption -> {
                        _caption.value = event.text
                    }
                    is Save -> {

                    }
                    is Delete -> {

                    }
                }

            } catch (e: Exception) {
                Log.e(
                    "BuildViewModel: ",
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
            // check database
            // if not empty remove images > 2 weeks old
            val dbImages = localService.getAllImages()
            val twoWeeksAgo: Date = convertLocalDateTimeToDate(LocalDateTime.now().minusWeeks(2L)) ?: Date(System.currentTimeMillis())
            localService.clearOldImages(twoWeeksAgo)

            if (dbImages.size < 300) {
                // ImgFLip
                val imgFlipResult: ImgFlip = ImgFlipApi.retrofitService.getImgFlipMeme()
                localService.insertImgFlipMemeImageList(imgFlipResult.data.memes)

                // Google Results
                val googleImageResult = GoogleImageApi.retrofitService.getGoogleImages()
                if (googleImageResult != null) {
                    localService.insertGoogleImages(googleImageResult)
                }
                // Pexel Results
                var pexelImages = PexelApi.retrofitService.getPexelMeme(PEXEL_API_KEY, "1")
                if (pexelImages != null) {
                    localService.insertPexelImageList(pexelImages)
                }
                pexelImages = PexelApi.retrofitService.getPexelMeme(PEXEL_API_KEY, "2")
                if (pexelImages != null) {
                    localService.insertPexelImageList(pexelImages)
                }
            }
            // update ImageList
            _imageList.value = localService.getAllImages()
            getImage()
            _loading = false
        }
    }

    private fun getImage() {
        if (_imageList.value.size > 0) {
            val rand = (0.._imageList.value.size - 1).shuffled().first()
            val imageSearchEntity = imageList.value[rand]
            _imageUrl.value = imageSearchEntity.imageUrl
        } else {
            getImageList()
        }
    }

    private fun getImageList() {
        _loading = true
        getImages()
        val rand = (0.._imageList.value.size - 1).shuffled().first()
        val imageSearchEntity = imageList.value.get(rand)
        _imageUrl.value = imageSearchEntity.imageUrl
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
            } catch (e: Exception) {
                Log.i("YoMamma", e.message + " with cause " + e.cause)
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

    private fun getjokeApiJoke() {
        viewModelScope.launch {
            try {
                val result = JokeApiService.JokeApi.retrofitService.getJokeApiJoke()
                if (result != null) {
                    _jokeApiJoke.value = result
                }
            } catch (e: Exception) {
                Log.i("JokeApiJoke", e.message + " with cause " + e.cause)
                _jokeApiJoke.value = JokeApi()
            }
            _loading = false
        }
    }

    private fun getAdvice() {
        viewModelScope.launch {
            try {
                val result = AdviceApiService.AdviceApi.retrofitService.getadviceJoke()
                if (result != null) {
                    _advice.value = result
                }
            } catch (e: Exception) {
                Log.i("Advice", e.message + " with cause " + e.cause)
                _advice.value = Advice()
            }
            _loading = false
        }
    }

    private fun getDadJoke() {
        viewModelScope.launch {
            try {
                val result = DadJokeApiService.DadJokeApi.retrofitService.getdadJoke()
                if (result != null) {
                    _dadJoke.value = result
                }
            } catch (e: Exception) {
                Log.i("Dad Joke", e.message + " with cause " + e.cause)
                _dadJoke.value = DadJokes()
            }
            _loading = false
        }
    }

    private fun getCatFact() {
        viewModelScope.launch {
            try {
                val result = CatFactApiService.CatFactApi.retrofitService.getcatFact()
                if (result != null) {
                    _catFact.value = result
                }
            } catch (e: Exception) {
                Log.i("Cat Fact", e.message + " with cause " + e.cause)
                _catFact.value = CatFact()
            }
            _loading = false
        }
    }

    private fun getDogFact() {
        viewModelScope.launch {
            try {
                val result = DogFactApiService.DogFactApi.retrofitService.getDogFact()
                if (result != null) {
                    _dogFact.value = result
                }
            } catch (e: Exception) {
                Log.i("Cat Fact", e.message + " with cause " + e.cause)
                _dogFact.value = DogFact()
            }
            _loading = false
        }
    }


}
