package com.easyprog.peil.data.models

data class LessonLearningSteps(
    val id: Int,
    val lessonPhrase: String,
    val newWorld: String,
    val newWorldTranslate: String,
    val answer: Boolean? = null,
    val typeView: Int,
    val idLessonDetail: Int
)