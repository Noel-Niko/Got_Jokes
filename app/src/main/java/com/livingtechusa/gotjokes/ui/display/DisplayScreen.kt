package com.livingtechusa.gotjokes.ui.display

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildScreen
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.MemeImgCard
import com.livingtechusa.gotjokes.ui.theme.JokesTheme
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DisplayScreen() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        DisplayScreenLandscape()
    } else {
        val buildViewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
        val caption by buildViewModel.caption.collectAsState()
        val image by buildViewModel.imageUrl.collectAsState()
        val textColor by buildViewModel.color.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (image == null) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Spacer(Modifier.height(16.dp))
                        if (image != null) {
                            MemeImgCard(url = image!!)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        var offsetY by remember { mutableStateOf(0f) }
                        Text(
                            modifier = Modifier
                                .offset { IntOffset(0, offsetY.roundToInt()) }
                                .draggable(
                                    orientation = Orientation.Vertical,
                                    state = rememberDraggableState { delta ->
                                        offsetY += delta
                                    }
                                )
                                .clickable {
                                    buildViewModel.onTriggerEvent(BuildEvent.UpdateColor)
                                },
                            text = caption,
                            fontSize = 20.sp,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                buildViewModel.onTriggerEvent(BuildEvent.Save)
                            }
                        ) {
                            Text(stringResource(R.string.save))
                        }
                    }
                }
            }
        }
    }
}