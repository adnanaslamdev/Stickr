package com.github.adnanaslamdev.stickr

import androidx.compose.runtime.Composable

object StickrDefaults {

    @Composable
    fun defaultControls(): List<StickrControl> {
        return listOf(
            StickrControl(
                type = StickrControlType.DELETE,
                iconResId = R.drawable.ic_close,
                isVisible = true,
            ),
            StickrControl(
                type = StickrControlType.DUPLICATE,
                iconResId = R.drawable.ic_duplicate,
                isVisible = true,
            ),
            StickrControl(
                type = StickrControlType.FLIP,
                iconResId = R.drawable.ic_flip,
                isVisible = true,
            ),
            StickrControl(
                type = StickrControlType.RESIZE,
                iconResId = R.drawable.ic_resize,
                isVisible = true,
            )
        )
    }

}
