package com.easyprog.peil.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(
    val id: String = "",
    val number: Int = 0,
    val name: String = "",
    val subtitle: String = "",
    val description: String = "",
    val time: String = "",
    val imageUrl: String = "",
    val iconUrl: String = "",
    val lessonLearned: Boolean = false
) : Parcelable
