package com.easyprog.peil.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.easyprog.core.navigator.Navigator
import com.easyprog.core.utils.Event
import com.easyprog.core.views.BaseViewModel
import com.easyprog.peil.data.models.LessonLearningSteps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LearningLessonViewModel @Inject constructor() : ViewModel() {

    private val _learningLessonStepsList = MutableLiveData<List<LessonLearningSteps>>()
    val learningLessonStepsList: LiveData<List<LessonLearningSteps>> = _learningLessonStepsList

    fun getData(idLessonDetails: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = createLearningLessonSteps()
            val filterData = ArrayList<LessonLearningSteps>()
            data.forEach {
                if (it.idLessonDetail == idLessonDetails) {
                    filterData.add(it)
                }
            }
            withContext(Dispatchers.Main) {
                _learningLessonStepsList.value = filterData
            }
        }
    }

    private fun createLearningLessonSteps(): List<LessonLearningSteps> {
        val mockData: MutableList<LessonLearningSteps> = ArrayList()
        mockData.add(
            LessonLearningSteps(
                id = 0,
                lessonPhrase = "Смотри-ка, кое-что новенькое!",
                newWorld = "Konnichiwa/こんにちは",
                newWorldTranslate = "Здравствуйте",
                typeView = 0,
                idLessonDetail = 0
            )
        )
        mockData.add(
            LessonLearningSteps(
                id = 1,
                lessonPhrase = "Верно или неверно?",
                newWorld = "Konnichiwa",
                newWorldTranslate = "Это значит \"Здравствуйте\"",
                answer = true,
                typeView = 1,
                idLessonDetail = 0
            )
        )

        return mockData
    }
}