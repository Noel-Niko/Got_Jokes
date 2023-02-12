package com.livingtechusa.gotjokes.ui.components.animation_utils

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun MultiTouchBox() {
    /**
     * Rotates the given offset around the origin by the given angle in degrees.
     *
     * A positive angle indicates a counterclockwise rotation around the right-handed 2D Cartesian
     * coordinate system.
     *
     * See: [Rotation matrix](https://en.wikipedia.org/wiki/Rotation_matrix)
     */
    fun Offset.rotateBy(angle: Float): Offset {
        val angleInRadians = angle * Math.PI / 180
        return Offset(
            (x * Math.cos(angleInRadians) - y * Math.sin(angleInRadians)).toFloat(),
            (x * Math.sin(angleInRadians) + y * Math.cos(angleInRadians)).toFloat()
        )
    }

    var offset = remember { mutableStateOf(Offset.Zero) }
    var zoom = remember { mutableStateOf(1f) }
    var angle = remember { mutableStateOf(0f) }


    Box(
        Modifier
            .pointerInput(Unit) {
                detectTransformGestures(
                    panZoomLock = false,
                    onGesture = { centroid, pan, gestureZoom, gestureRotate ->
                        val oldScale = zoom
                        val newScale = (zoom.value * gestureZoom)

                        // For natural zooming and rotating, the centroid of the gesture should
                        // be the fixed point where zooming and rotating occurs.
                        // We compute where the centroid was (in the pre-transformed coordinate
                        // space), and then compute where it will be after this delta.
                        // We then compute what the new offset should be to keep the centroid
                        // visually stationary for rotating and zooming, and also apply the pan.
                        offset.value =
                            (offset.value + centroid / oldScale.value).rotateBy(gestureRotate) -
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
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxSize()
    ) {
    }
}