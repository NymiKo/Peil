package com.easyprog.peil.data

import com.easyprog.peil.data.models.Lesson
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSourceImpl @Inject constructor() : FirebaseSource {

    private companion object {
        private const val COLLECTION_LESSONS = "lessons"
    }

    private lateinit var firestore: FirebaseFirestore

    private fun getFirestore(): FirebaseFirestore {
        if (!::firestore.isInitialized) {
            firestore = Firebase.firestore
        }
        return firestore
    }

    override suspend fun getLessonsList(): Result<List<Lesson>> {
        val snapshot = getFirestore().collection(COLLECTION_LESSONS).orderBy("number").get().await()
        return getResult(snapshot)
    }

    private inline fun <reified T : Any> getResult(snapshot: QuerySnapshot): com.easyprog.peil.data.Result<List<T>> {
        return try {
            if (!snapshot.isEmpty) {
                com.easyprog.peil.data.Result.SUCCESS(snapshot.toObjects(T::class.java))
            } else {
                com.easyprog.peil.data.Result.ERROR("error")
            }
        } catch (e: Exception) {
            com.easyprog.peil.data.Result.ERROR(e.message.toString())
        }
    }

}