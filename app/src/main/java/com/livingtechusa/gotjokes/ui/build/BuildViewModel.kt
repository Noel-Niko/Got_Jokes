package com.livingtechusa.gotjokes.ui.build

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.livingtechusa.gotjokes.domain.model.Image
import com.livingtechusa.gotjokes.domain.model.Joke
import com.livingtechusa.gotjokes.repositories.image_repos.imgflip.ImgFlipRepository
import com.livingtechusa.gotjokes.ui.build.BuildEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

const val STATE_KEY_URL = "com.livingtechusa.gotjokes.ui.build.joke.url"



@HiltViewModel
class BuildViewModel
@Inject
constructor(
    private val imgFlipRepository: ImgFlipRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _joke = MutableLiveData<Joke>(Joke())
    val joke: LiveData<Joke>
        get() = _joke
    val imgFlipList: MutableList<Image> = mutableListOf()

    val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
            get() = _loading



    init {
        _loading.value = true
        if(state.get<String>(STATE_KEY_URL) == "com.livingtechusa.gotjokes.ui.build.joke.url") {
            state.get<String>(STATE_KEY_URL)?.let { imgFlipUrl ->
                _joke.value?.image = imgFlipUrl
            } ?: onTriggerEvent(GetImgFlipImages)
        } else {
            onTriggerEvent(GetImgFlipImages)
        }
        _loading.value = false
    }


    fun onTriggerEvent(event: BuildEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetImgFlipImages -> { getImgFlipImageList() }
                    is GetNewImgFlipImage -> {
                        val rand = Random.nextInt(0, imgFlipList.size - 1)
                        getImgFlipImage(rand)
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

    private suspend fun getImgFlipImage(rand: Int) {
        if(imgFlipList.size > 0) {
            val url = imgFlipList.get(rand).url
            _joke.value?.image = url
        } else {
            getImgFlipImageList()
        }
    }

    private suspend fun getImgFlipImageList() {
        _loading.value = true
        imgFlipList.addAll(imgFlipRepository.get())
        val rand: Int = Random.nextInt(0, imgFlipList.size - 1)
        val url = imgFlipList.get(rand).url
        _joke.value?.image = url
        _loading.value = false
    }

}
