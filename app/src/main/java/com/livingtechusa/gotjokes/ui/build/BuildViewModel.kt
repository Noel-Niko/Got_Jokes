package com.livingtechusa.gotjokes.ui.build

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.view.Window
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.BaseApplication
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
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity
import com.livingtechusa.gotjokes.data.database.localService.LocalServiceProvider
import com.livingtechusa.gotjokes.network.AdviceApiService
import com.livingtechusa.gotjokes.network.CatFactApiService
import com.livingtechusa.gotjokes.network.DadJokeApiService
import com.livingtechusa.gotjokes.network.DogFactApiService
import com.livingtechusa.gotjokes.network.GoogleImageApi
import com.livingtechusa.gotjokes.network.ImgFlipApi
import com.livingtechusa.gotjokes.network.JokeApiService
import com.livingtechusa.gotjokes.network.PexelApi
import com.livingtechusa.gotjokes.network.RandomFactsApiService
import com.livingtechusa.gotjokes.network.YoMammaApi
import com.livingtechusa.gotjokes.network.YodaApiService
import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
import com.livingtechusa.gotjokes.util.Constants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

const val STATE_KEY_URL = "com.livingtechusa.gotjokes.ui.build.joke.url"

enum class ApiStatus { PRE_INIT, LOADING, ERROR, DONE }

@HiltViewModel
class BuildViewModel @Inject constructor(
    private val localService: LocalServiceProvider
) : ViewModel() {

    //    private val _status = MutableStateFlow(ApiStatus.PRE_INIT)
    //    val status: StateFlow<ApiStatus>
    //        get() = _status
    private val context: Context
        get() = BaseApplication.getInstance()

    private val _imageList = MutableStateFlow(emptyList<ImageSearchEntity>())
    val imageList: StateFlow<List<ImageSearchEntity>> get() = _imageList

    private val _imageUrl = MutableStateFlow(String())
    val imageUrl: StateFlow<String?> get() = _imageUrl

    private val _caption = MutableStateFlow(String())
    val caption: StateFlow<String> get() = _caption

    private val _yodaSpeak = MutableStateFlow(String())
    val yodaSpeak: StateFlow<String> get() = _yodaSpeak

    private val _yoMamma = MutableStateFlow(YoMamma(""))
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

    val jokes: LiveData<List<JokeEntity>> = localService.getAllJokes().asLiveData()

    val _color: MutableStateFlow<Color> = MutableStateFlow(Color.Black)
    val color: StateFlow<Color> get() = _color

    var _loading: Boolean by mutableStateOf(false)
    val loading: Boolean get() = _loading


    init {
        _loading = true
        _color.value = Color.Black
        getImages()
        getYoMammaJokes()
        getRandomFacts()
        getjokeApiJoke()
        getAdvice()
        getDadJoke()
        getCatFact()
        getDogFact()
    }

    fun onTriggerEvent(event: BuildEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetImages -> {
                        _caption.value = EMPTY_STRING
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
                        _caption.value = EMPTY_STRING
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
                        val imageUri = event.imgURI
//                        val openDocument = rememberLauncherForActivityResult(
//                            contract = ActivityResultContracts.OpenDocument(),
//                        ) { uri ->
//                            uri?.let {
//                                    imageUri = it
//                            }
//                         }
//    val getPictureIntent = Intent(MediaStore.ACTION_REVIEW)
//    try {
//        startActivityForResult(MainActivity(), getPictureIntent, 0, null)
//    } catch (e: ActivityNotFoundException) {
//        // display error state to the user
//    }
//                        val shareIntent: Intent = Intent().apply {
//                            action = Intent.ACTION_SEND
//                            putExtra(Intent.EXTRA_STREAM, imageUri)
//                            type = "image/jpeg"
//                        }
//                      BaseApplication.getInstance().applicationContext.startActivity(Intent.createChooser(shareIntent, null), null)
                        val joke = JokeEntity(
                            imageUrl = imageUrl.value.toString(),
                            caption = caption.value,
                            dateAdded = Date(System.currentTimeMillis()),
                            imgURI = imageUri
                        )
                        localService.insertJoke(joke)
                    }

                    is Delete -> {
                        viewModelScope.launch {
                            localService.deleteJoke(joke = event.joke)
                        }
                    }

                    is Share -> {
                        val meme = event.joke.imgURI
                        val shareIntent = Intent()
                        shareIntent.action = Intent.ACTION_SEND
                        val resolver: ContentResolver = context.contentResolver
                        shareIntent.action = Intent.ACTION_OPEN_DOCUMENT
                        shareIntent.type = "image/*"
                        shareIntent.setDataAndType(meme, meme?.let { resolver.getType(it) })
                        shareIntent.putExtra(Intent.EXTRA_STREAM, meme)
                        shareIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            "\n\nCreated with Got Jokes, available on Google Play."
                        )
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        shareIntent.action = Intent.ACTION_SEND
                        ContextCompat.startActivity(context, shareIntent, null)
                    }

                    is UpdateColor -> {
                        when (_color.value) {
                            Color.Black -> _color.value = Color.Gray
                            Color.Gray -> _color.value = Color.White
                            Color.White -> _color.value = Color.Yellow
                            Color.Yellow -> _color.value = Color.Blue
                            Color.Blue -> _color.value = Color.Black
                            else -> _color.value = Color.Black
                        }
                    }

                    is ResetColor -> {
                        _color.value = Color.Black
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
            val twoWeeksAgo: Date = convertLocalDateTimeToDate(LocalDateTime.now().minusWeeks(2L))
                ?: Date(System.currentTimeMillis())
            localService.clearOldImages(twoWeeksAgo)

            if (dbImages.size < 200) {
                // ImgFLip
                val imgFlipResult: ImgFlip? = ImgFlipApi.retrofitService.getImgFlipMeme()
                imgFlipResult?.data?.memes?.let { localService.insertImgFlipMemeImageList(it) }

                // Google Results
                val googleImageResult = GoogleImageApi.retrofitService.getGoogleImages()
                if (googleImageResult != null) {
                    localService.insertGoogleImages(googleImageResult)
                }
                val googleImageResult2 = GoogleImageApi.retrofitService.getNextPageGoogleImages()
                if (googleImageResult2 != null) {
                    localService.insertGoogleImages(googleImageResult2)
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

    private suspend fun getImage() {
        if (_imageList.value.size > 10) {
            localService.removeOneImage((_imageUrl.value))
            _imageList.value = localService.getAllImages()
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
                if (result != null) {
                    _yoMamma.value = result
                }
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

    private val _screenshot = MutableLiveData<Bitmap>()
    val screenshot: LiveData<Bitmap> = _screenshot

    fun copyViewScreenshotIntoBitmap(view: View, window: Window) {
//        PixelCopyHelper.getViewBitmap(view, window, object : PixelCopyHelper.PixelCopyListener {
//            override fun onCopySuccess(bitmap: Bitmap) {
//                _screenshot.postValue(bitmap)
//            }
//
//            override fun onCopyError() {
//                // Handle error case
//            }
//        })
    }


}
