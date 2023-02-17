package com.livingtechusa.gotjokes.ui.display

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.livingtechusa.gotjokes.R
import com.livingtechusa.gotjokes.ui.build.BuildEvent
import com.livingtechusa.gotjokes.ui.build.BuildViewModel
import com.livingtechusa.gotjokes.ui.components.DisplayImgCard
import com.livingtechusa.gotjokes.util.TakeScreenShot
import com.livingtechusa.gotjokes.util.findActivity
import kotlin.math.roundToInt

@Composable
fun DisplayScreen() {

    val activity = findActivity()
    TakeScreenShot.verifyStoragePermission(
        activity
    )

    val configuration = LocalConfiguration.current
    val height = configuration.screenHeightDp
    val width = configuration.screenWidthDp
    val viewModel: BuildViewModel = viewModel(BuildViewModel::class.java)
    val caption by viewModel.caption.collectAsState()
    val image by viewModel.imageUrl.collectAsState()
    val textColor by viewModel.color.collectAsState()
    val offSetX = remember { mutableStateOf(0f) }
    val offSetY = remember { mutableStateOf(0f) }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { owner, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.onTriggerEvent(BuildEvent.ResetColor)
            }
        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)
        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val fontSize = remember {
        mutableStateOf((height * .025).toInt())
    }

    fun Offset.rotateBy(angle: Float): Offset {
        val angleInRadians = angle * Math.PI / 180
        return Offset(
            (x * Math.cos(angleInRadians) - y * Math.sin(angleInRadians)).toFloat(),
            (x * Math.sin(angleInRadians) + y * Math.cos(angleInRadians)).toFloat()
        )
    }

    val offset = remember { mutableStateOf(Offset.Zero) }
    val zoom = remember { mutableStateOf(1f) }
    val angle = remember { mutableStateOf(0f) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight((height / 5) * 3.5f)
        ) {
            if (image == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Spacer(
                        Modifier.height((height / 5).dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (image != null) {
                        DisplayImgCard(url = image!!)
                    }
                }
            }
        }
        Box(modifier = Modifier
            .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .padding(55.dp, 0.dp, 55.dp, 0.dp)
                    .offset() {
                        IntOffset(
                            x = offSetX.value.roundToInt(),
                            y = offSetY.value.roundToInt()
                        )
                    }
                    .pointerInput(Unit) {
                        detectTransformGestures(
                            panZoomLock = false,
                            onGesture = { centroid, pan, gestureZoom, gestureRotate ->
                                val oldScale = zoom
                                val newScale = (zoom.value * gestureZoom)
                                offset.value =
                                    (offset.value + centroid / oldScale.value).rotateBy(
                                        gestureRotate
                                    ) -
                                            (centroid / newScale + pan / oldScale.value)
                                zoom.value = newScale
                                angle.value += gestureRotate
                            }
                        )
                    }
                    .graphicsLayer {
                        translationX = -offset.value.x * zoom.value
                        translationY = -offset.value.y * zoom.value
                        scaleX = zoom.value
                        scaleY = zoom.value
                        rotationZ = angle.value
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                    .background(Color.Transparent)
                    .clickable {
                        viewModel.onTriggerEvent(BuildEvent.UpdateColor)
                    }
                    .fillMaxSize(),
                text = caption,
                fontSize = fontSize.value.sp,
                color = textColor
            )
        }

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                try {

                    val imageUri = TakeScreenShot.takeScreenShot(
                        activity.window.decorView,
                        caption,
                        (height * 2).dp,
                        width.dp
                    )
                    val uri: Uri = imageUri

                    viewModel.onTriggerEvent(BuildEvent.Save(uri))
                    Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    val TAG = "ScreenShot"
                    Log.e(
                        TAG,
                        "Error message: " + e.message + " with cause " + e.cause
                    )
                    Toast.makeText(
                        activity,
                        "Unable to save. \n Error: " + e.cause,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        ) {
            Text(stringResource(R.string.save))
        }
    }
}