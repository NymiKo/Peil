package com.easyprog.core.utils

import android.widget.ImageView
import androidx.navigation.NavController
import com.bumptech.glide.Glide

fun NavController.popBackStackAllInstances(destination: Int, inclusive: Boolean): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, inclusive)
        if (!popped) {
            break
        }
    }
    return popped
}

fun <T> ImageView.loadImage(image: T) {
    Glide.with(this).load(image).fitCenter().centerCrop().into(this)
}