package com.livingtechusa.gotjokes.ui.build

import android.graphics.Bitmap
import android.net.Uri
import androidx.navigation.NavHostController
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity

sealed class BuildEvent {

    object GetImages : BuildEvent()
    object GetNewImage : BuildEvent()
    data class ConvertToYodaSpeak(
        val text: String
    ) : BuildEvent()

    data class UpdateCaption(
        val text: String
    ) : BuildEvent()

    data class Save(
        val imgURI: Uri
    ) : BuildEvent()

    data class Delete(
        val joke: JokeEntity
        ) : BuildEvent()

    object UpdateColor : BuildEvent()
}