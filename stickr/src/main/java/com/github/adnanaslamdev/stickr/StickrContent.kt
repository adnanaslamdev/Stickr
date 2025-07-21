package com.github.adnanaslamdev.stickr

import android.graphics.Bitmap

sealed class StickrContent {
    data class DrawableRes(val resId: Int) : StickrContent()
    data class BitmapImage(val bitmap: Bitmap) : StickrContent()
}