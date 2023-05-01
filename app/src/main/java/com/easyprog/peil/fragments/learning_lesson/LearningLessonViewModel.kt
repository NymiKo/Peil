package com.easyprog.peil.fragments.learning_lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyprog.peil.data.Result
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonLearningSteps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LearningLessonViewModel @Inject constructor(
    private val repository: LearningLessonRepository
) : ViewModel() {

    private val _learningLessonStepsList = MutableLiveData<Result<List<LessonLearningSteps>>>()
    val learningLessonStepsList: LiveData<Result<List<LessonLearningSteps>>> = _learningLessonStepsList

    fun getData(idLesson: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_learningLessonStepsList.value == null || _learningLessonStepsList.value != emptyList<Lesson>()) {
                _learningLessonStepsList.postValue(Result.LOADING)
                val movieList = repository.getLessonsDetails(idLesson)
                _learningLessonStepsList.postValue(movieList)
            }
        }
    }

//    private fun createLearningLessonSteps(): List<LessonLearningSteps> {
//        val mockData: MutableList<LessonLearningSteps> = ArrayList()
//        mockData.add(
//            LessonLearningSteps(
//                lessonPhrase = "Смотри-ка, кое-что новенькое!",
//                newWorld = "Konnichiwa/こんにちは",
//                description = "Здравствуйте",
//                typeView = 0
//            )
//        )
//        mockData.add(
//            LessonLearningSteps(
//                lessonPhrase = "Верно или неверно?",
//                newWorld = "Konnichiwa",
//                description = "Это значит \"Здравствуйте\"",
//                typeView = 1
//            )
//        )
//
//        return mockData
//    }
}