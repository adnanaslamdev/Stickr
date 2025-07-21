package com.github.adnanaslamdev.stickr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun Stickr(
    content: StickrContent,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    showControls: Boolean = true,
    controls: List<StickrControl> = StickrDefaults.defaultControls(),
    borderColor: Color = Color.Black,
    borderWidth: Dp = 2.dp,
    borderShape: RoundedCornerShape = RoundedCornerShape(0.dp),
    onSelect: () -> Unit = {},
    onDelete: () -> Unit = {},
    onDuplicate: () -> Unit = {},
    onFlip: () -> Unit = {},
) {
    var offset by remember { mutableStateOf(Offset(200f, 200f)) }
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var isFlipped by remember { mutableStateOf(false) }

    val gestureModifier = if (isSelected) {
        Modifier.pointerInput(Unit) {
            detectTransformGestures { _, pan, zoom, rotate ->
                offset += pan
                scale = (scale * zoom).coerceIn(0.3f, 3f)
                rotation += rotate
                onSelect()
            }
        }
    } else Modifier

    val painter: Painter = when (content) {
        is StickrContent.DrawableRes -> painterResource(id = content.resId)
        is StickrContent.BitmapImage -> BitmapPainter(content.bitmap.asImageBitmap())
    }

    Box(
        modifier = modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = { onSelect() })
            }
            .then(gestureModifier)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .border(
                        width = if (isSelected) borderWidth else 0.dp,
                        color = if (isSelected) borderColor else Color.Transparent,
                        shape = borderShape
                    )
            )
            Image(
                painter = painter,
                contentDescription = "Sticker",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(borderShape)
                    .graphicsLayer {
                        scaleX = if (isFlipped) -1f else 1f
                    }
            )

            if (isSelected && showControls) {
                controls.filter { it.isVisible }.forEach { control ->
                    val controlModifier = Modifier
                        .size(28.dp)
                        .background(Color.White, CircleShape)
                        .border(1.dp, Color.Black, CircleShape)

                    when (control.type) {
                        StickrControlType.DELETE -> {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.TopStart)
                                    .absoluteOffset(x = (-16).dp, y = (-16).dp) // pull it outside
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape)
                                    .clickable { onDelete() },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Delete",
                                    tint = Color.Red,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        StickrControlType.DUPLICATE -> {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.TopEnd)
                                    .absoluteOffset(x = (16).dp, y = (-16).dp) // pull it outside
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape)
                                    .clickable { onDuplicate() },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(control.iconResId),
                                    contentDescription = "Duplicate",
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        StickrControlType.FLIP -> {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.BottomStart)
                                    .absoluteOffset(x = (-16).dp, y = (16).dp) // pull it outside
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape)
                                    .clickable {
                                        isFlipped = !isFlipped
                                        onFlip()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(control.iconResId),
                                    contentDescription = "Flip",
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        StickrControlType.RESIZE -> {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.BottomEnd)
                                    .absoluteOffset(x = (16).dp, y = (16).dp) // pull it outside
                                    .background(Color.White, CircleShape)
                                    .border(1.dp, Color.Black, CircleShape)
                                    .pointerInput(Unit) {
                                        detectDragGestures { change, dragAmount ->
                                            change.consume()
                                            val delta = dragAmount.x + dragAmount.y
                                            scale = (scale + delta * 0.005f).coerceIn(0.3f, 3f)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(control.iconResId),
                                    contentDescription = "Resize",
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}