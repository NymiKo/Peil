package com.easyprog.peil.data.models

data class LessonLearningSteps(
    val id: String = "",
    val lessonPhrase: String = "",
    val newWord: String = "",
    val description: String = "",
    val practice: Boolean = false,
    val typeView: Int = 0
)