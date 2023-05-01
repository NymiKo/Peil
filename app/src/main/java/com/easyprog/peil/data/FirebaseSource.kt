package com.easyprog.peil.data

import com.easyprog.peil.data.models.Lesson

interface FirebaseSource {

    suspend fun getLessonsList(): Result<List<Lesson>>

}