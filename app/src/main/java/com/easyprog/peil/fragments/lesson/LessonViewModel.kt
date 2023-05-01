package com.easyprog.peil.fragments.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easyprog.peil.data.models.LessonDetails
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
        mockData.add(LessonDetails(0, "Словарь", "Выучи новые слова и выражения.", false, "Y74RqgWKEQl706JdE1yS"))
        mockData.add(LessonDetails(9, "Запоминай", "Закрепи свои знания новых слов и выражений", false, "2"))
        mockData.add(LessonDetails(11, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, "2"))
        mockData.add(LessonDetails(3, "Словарь", "Выучи новые слова и выражения.", true, "m5gheGjGUr54zfu0n7Vj"))
        mockData.add(LessonDetails(4, "Практикуйся", "Строй предложения и используй язык в контексте", true, "m5gheGjGUr54zfu0n7Vj"))
        mockData.add(LessonDetails(5, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, "m5gheGjGUr54zfu0n7Vj"))

        return mockData
    }

}