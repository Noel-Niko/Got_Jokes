package com.livingtechusa.gotjokes.ui.build

import androidx.navigation.NavHostController

sealed class BuildEvent {

    object GetImgFlipImages : BuildEvent()
    object GetNewImgFlipImage : BuildEvent()
    data class ConvertToYodaSpeak(
        val text: String
    ) : BuildEvent()

    data class UpdateCaption(
        val text: String
    ) : BuildEvent()

}