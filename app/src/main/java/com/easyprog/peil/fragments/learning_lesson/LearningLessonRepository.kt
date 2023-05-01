package com.easyprog.peil.fragments.learning_lesson

import com.easyprog.peil.data.Result
import com.easyprog.peil.data.models.LessonLearningSteps

interface LearningLessonRepository {
    suspend fun getLessonsDetails(idLesson: String): Result<List<LessonLearningSteps>>
}