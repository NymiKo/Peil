package com.easyprog.peil.data.models

import android.os.Parcelable
import com.easyprog.peil.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(
    val id: Int,
    val number: String,
    val name: String,
    val description: String,
    val time: String,
    val imageUrl: Int = R.drawable.japan,
    val iconUrl: Int = R.drawable.ic_baseline_do_not_disturb_alt,
    val lessonLearned: Boolean
) : Parcelable
