package com.easyprog.peil.adapters

import android.view.View
import com.easyprog.peil.data.models.Lesson

interface LessonActionListener {

    fun onLessonDetails(lesson: Lesson, view: View)

}