package com.github.adnanaslamdev.stickr

data class StickrControl(
    val type: StickrControlType,
    val iconResId: Int,
    val isVisible: Boolean = true
)
