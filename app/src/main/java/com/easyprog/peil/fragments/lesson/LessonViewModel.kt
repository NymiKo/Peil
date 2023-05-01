package com.easyprog.peil.fragments.lesson

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.easyprog.core.utils.Event
import com.easyprog.core.views.BaseViewModel
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonDetails
import com.easyprog.core.navigator.Navigator
import com.easyprog.peil.fragments.LearningLessonFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(): ViewModel() {

    private val _lessonDetails = MutableLiveData<List<LessonDetails>>()
    val lessonDetails: LiveData<List<LessonDetails>> = _lessonDetails

    fun getData(idLesson: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = createLessonDetails()
            val filterData = ArrayList<LessonDetails>()
            data.forEach {
                if (it.idLesson == idLesson) {
                    filterData.add(it)
                }
            }
            withContext(Dispatchers.Main) {
                _lessonDetails.value = filterData
            }
        }
    }

    private fun createLessonDetails(): List<LessonDetails> {
        val mockData: MutableList<LessonDetails> = ArrayList()
        mockData.add(LessonDetails(0, "Словарь", "Выучи новые слова и выражения.", false, "0"))
        mockData.add(LessonDetails(9, "Запоминай", "Закрепи свои знания новых слов и выражений", false, "2"))
        mockData.add(LessonDetails(11, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, "2"))
        mockData.add(LessonDetails(3, "Словарь", "Выучи новые слова и выражения.", true, "1"))
        mockData.add(LessonDetails(4, "Практикуйся", "Строй предложения и используй язык в контексте", true, "1"))
        mockData.add(LessonDetails(5, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, "1"))

        return mockData
    }

}