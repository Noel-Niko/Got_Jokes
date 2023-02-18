package com.livingtechusa.gotjokes.ui.display

sealed class DisplayEvent {
    object GoToBuildScreen : DisplayEvent()
    object GoToSavedScreen : DisplayEvent()
    data class DeleteMeme(
        val imageUrl: String,
        val caption: String
    ) : DisplayEvent()
}