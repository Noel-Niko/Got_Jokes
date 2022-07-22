package com.livingtechusa.gotjokes.ui.build

import androidx.navigation.NavHostController

sealed class BuildEvent {

    object GetImages : BuildEvent()
    object GetNewImage : BuildEvent()
    data class ConvertToYodaSpeak(
        val text: String
    ) : BuildEvent()

    data class UpdateCaption(
        val text: String
    ) : BuildEvent()

    object Save : BuildEvent()

    object Delete : BuildEvent()
}