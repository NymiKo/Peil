package com.easyprog.peil.data

import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonLearningSteps

interface FirebaseSource {

    suspend fun getLessonsList(): Result<List<Lesson>>
    suspend fun getLessonsDetails(idLesson: String): Result<List<LessonLearningSteps>>

}