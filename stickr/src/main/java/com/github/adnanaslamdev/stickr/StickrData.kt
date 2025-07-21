package com.github.adnanaslamdev.stickr

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize

data class StickrData(
    val id: Int,
    val content: StickrContent,
    var position: Offset = Offset(200f, 200f),
    var scale: Float = 1f,
    var rotation: Float = 0f,
    var isFlipped: Boolean = false
)