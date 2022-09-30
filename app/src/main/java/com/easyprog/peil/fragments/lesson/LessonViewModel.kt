package com.easyprog.peil.fragments.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.easyprog.core.utils.Event
import com.easyprog.core.views.BaseViewModel
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonDetails
import com.easyprog.core.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonViewModel constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
    screen: LessonFragment.Screen
): BaseViewModel() {

    private val _lesson = MutableLiveData<Event<Lesson>>()
    val lesson: LiveData<Event<Lesson>> = _lesson

    private val _lessonDetails = MutableLiveData<List<LessonDetails>>()
    val lessonDetails: LiveData<List<LessonDetails>> = _lessonDetails

    init {
        _lesson.value = Event(screen.lesson)
        getData(screen.lesson.id)
    }

    fun onBackPressed() {
        navigator.goBack()
    }

    private fun getData(idLesson: Int) {
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
        mockData.add(LessonDetails(0, "Словарь", "Выучи новые слова и выражения.", false, 0))
        mockData.add(LessonDetails(1, "Запоминай", "Закрепи свои знания новых слов и выражений", false, 0))
        mockData.add(LessonDetails(2, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, 0))
        mockData.add(LessonDetails(3, "Словарь", "Выучи новые слова и выражения.", true, 1))
        mockData.add(LessonDetails(4, "Практикуйся", "Строй предложения и используй язык в контексте", true, 1))
        mockData.add(LessonDetails(5, "Тест", "Првоерь, хорошо ли тобой усвоен изученный материал.", false, 1))

        return mockData
    }

}