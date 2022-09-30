package com.easyprog.peil.fragments.lessons_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.easyprog.peil.R
import com.easyprog.core.views.BaseViewModel
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.fragments.lesson.LessonFragment
import com.easyprog.core.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//@HiltViewModel
class LessonsListViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle,
    screen: LessonsListFragment.Screen
): BaseViewModel() {

    private val _lessonsList = savedStateHandle.getLiveData<List<Lesson>>("lessons")
    val lessonsList: LiveData<List<Lesson>> = _lessonsList

    private val _expandedLessons = savedStateHandle.getLiveData<List<Lesson>>("expanded_lessons")
    val expandedLessons: LiveData<List<Lesson>> = _expandedLessons

    init {
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = createData()
            withContext(Dispatchers.Main) {
                _lessonsList.value = data
            }
        }
    }

    fun setExpandedLessons(expandedLessons: List<Lesson>) {
        _expandedLessons.value = expandedLessons
    }

    fun onLessonPressed(lesson: Lesson) {
        navigator.launch(LessonFragment.Screen(lesson))
    }

    private fun createData(): List<Lesson> {
        val mockData: MutableList<Lesson> = ArrayList()
        mockData.add(Lesson(0, "Урок 1", "Я изучаю японский", "", "1 минута", R.drawable.lesson_one, lessonLearned = true))
        mockData.add(Lesson(1, "Урок 2", "Катакана: KA KI KU KE KO", "Катакана: KA KI KU KE KO", "3 минуты", R.drawable.lesson_two, lessonLearned = true))
        mockData.add(Lesson(2, "Урок 3", "Повторение катаканы (часть 1)", "Повторение всех букв катаканы.", "6 минут", lessonLearned = true))
        mockData.add(Lesson(3, "Урок 4", "Повторение катаканы (часть 2)", "Повторение всех букв катаканы.", "4 минуты", lessonLearned = false))
        mockData.add(Lesson(4, "Урок 5", "Давай посчитаем по-японски!", "Давай посчитаем по-японски!", "10 минут", lessonLearned = false))
        mockData.add(Lesson(5, "Урок 6", "Катакана: SA SHI SU SE SO", "Катакана: SA SHI SU SE SO", "11 минут", lessonLearned = false))
        mockData.add(Lesson(6, "Урок 7", "Катакана: TA CHI TSU TE TO", "Катакана: TA CHI TSU TE TO", "7 минут", lessonLearned = false))

        return mockData
    }
}