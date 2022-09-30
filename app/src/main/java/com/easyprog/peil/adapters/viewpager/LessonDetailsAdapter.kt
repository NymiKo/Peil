package com.easyprog.peil.adapters.viewpager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.peil.R
import com.easyprog.peil.adapters.viewpager.LessonDetailsAdapter.LessonDetailsViewHolder
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonDetails
import com.easyprog.peil.databinding.ItemLessonDetails1Binding

class LessonDetailsAdapter : RecyclerView.Adapter<LessonDetailsViewHolder>() {

    var mLessonsDetailsList: List<LessonDetails> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonDetails1Binding.inflate(inflater, parent, false)
        return LessonDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonDetailsViewHolder, position: Int) {
        val lessonDetails = mLessonsDetailsList[position]
        with(holder.binding){
            textDescription.text = lessonDetails.description
            textNameLesson.text = lessonDetails.name
            if (lessonDetails.lessonLearned) {
                buttonStartOrRepeat.setBackgroundResource(R.drawable.button_lesson_repeat)
                buttonStartOrRepeat.text = "Повторить"
            }
        }
    }

    override fun getItemCount(): Int = mLessonsDetailsList.size

    class LessonDetailsViewHolder(val binding: ItemLessonDetails1Binding) :
        RecyclerView.ViewHolder(binding.root)

}