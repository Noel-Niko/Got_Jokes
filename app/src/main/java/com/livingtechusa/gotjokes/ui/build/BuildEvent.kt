package com.livingtechusa.gotjokes.ui.build

sealed class BuildEvent {

    object GetImgFlipImages : BuildEvent()
    object GetNewImgFlipImage : BuildEvent()
}