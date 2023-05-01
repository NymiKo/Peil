package com.easyprog.peil.fragments.lessons_list

import com.easyprog.peil.data.FirebaseSource
import com.easyprog.peil.data.Result
import com.easyprog.peil.data.models.Lesson
import javax.inject.Inject

class LessonsListRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): LessonsListRepository {

    override suspend fun getMovieList(): Result<List<Lesson>> = firebaseSource.getLessonsList()

}