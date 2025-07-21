package com.github.adnanaslamdev.stickr

object Utility {

    fun generateUniqueId(): Int =
        (System.currentTimeMillis() % Int.MAX_VALUE).toInt()

}