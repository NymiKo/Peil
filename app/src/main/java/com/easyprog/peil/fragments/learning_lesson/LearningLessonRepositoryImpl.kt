package com.easyprog.peil.fragments.learning_lesson

import com.easyprog.peil.data.FirebaseSource
import com.easyprog.peil.data.Result
import com.easyprog.peil.data.models.LessonLearningSteps
import javax.inject.Inject

class LearningLessonRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): LearningLessonRepository {
    override suspend fun getLessonsDetails(idLesson: String): Result<List<LessonLearningSteps>> = firebaseSource.getLessonsDetails(idLesson)
}