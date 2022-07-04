package com.livingtechusa.gotjokes.ui.build

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.data.api.model.imgFlip
import com.livingtechusa.gotjokes.data.repository.ImgFlipRepo
import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val STATE_KEY_URL = "com.livingtechusa.gotjokes.ui.build.joke.url"


@HiltViewModel
class BuildViewModel
@Inject
constructor(
    private val imgFlipRepository: ImgFlipRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<imgFlip.Data.Meme>())
    val state: StateFlow<List<imgFlip.Data.Meme>>
        get() = _state

    init {
        viewModelScope.launch {
            val memes = imgFlipRepository.getImgFlipApi()
            _state.value = memes
        }
    }

    //    private val _joke  by mutableStateOf(Joke())     /// = MutableLiveData<Joke>(Joke())
    //    val joke: Joke get() = _joke
    //    val imgFlipList: MutableList<Image> = mutableListOf()
    //
    //    var _loading: Boolean by mutableStateOf(false)
    //    val loading: Boolean get() = _loading

    // var joke by mutableStateOf(Joke())

    //    init {
    //        _loading.value = true
    //        if(state.get<String>(STATE_KEY_URL) == "com.livingtechusa.gotjokes.ui.build.joke.url") {
    //            state.get<String>(STATE_KEY_URL)?.let { imgFlipUrl ->
    //                joke.image = imgFlipUrl
    //            } ?: onTriggerEvent(GetImgFlipImages)
    //        } else {
    //            onTriggerEvent(GetImgFlipImages)
    //        }
    //        _loading.value = false
    //    }


//    fun onTriggerEvent(event: BuildEvent) {
//        viewModelScope.launch {
//            try {
//                when (event) {
//                    is GetImgFlipImages -> {
//                        getImgFlipImageList()
//                    }
//                    is GetNewImgFlipImage -> {
//                        val rand = Random.nextInt(0, imgFlipList.size - 1)
//                        getImgFlipImage(rand)
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(
//                    "BuildViewModel - ImgFlip: ",
//                    "Exception: ${e.message}  with cause: ${e.cause}"
//                )
//            }
//        }
//    }
//
//    private suspend fun getImgFlipImage(rand: Int) {
//        if (imgFlipList.size > 0) {
//            val url = imgFlipList.get(rand).url.toString()
//            joke.image = url
//        } else {
//            getImgFlipImageList()
//        }
//    }
//
//    private suspend fun getImgFlipImageList() {
//        _loading = true
//        imgFlipList.addAll(imgFlipRepository.get())
//        val rand: Int = Random.nextInt(0, imgFlipList.size - 1)
//        val url = imgFlipList.get(rand).url
//        joke.image = url.toString()
//        _loading = false
//    }

//    //notify on user input
//    fun UiUpdatedByUser(joke: Joke, bool: Boolean) {
//        _joke.chuckNorris = "Bad Ass"
//    }

}
