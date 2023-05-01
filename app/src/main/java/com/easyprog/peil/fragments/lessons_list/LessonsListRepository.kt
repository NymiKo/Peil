package com.easyprog.peil.fragments.lessons_list

import com.easyprog.peil.data.Result
import com.easyprog.peil.data.models.Lesson

interface LessonsListRepository {

    suspend fun getMovieList(): Result<List<Lesson>>
}