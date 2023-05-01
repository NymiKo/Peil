package com.easyprog.peil.adapters.viewpager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.peil.R
import com.easyprog.peil.adapters.viewpager.LessonDetailsAdapter.LessonDetailsViewHolder
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.data.models.LessonDetails
import com.easyprog.peil.databinding.ItemLessonDetailsBinding

class LessonDetailsAdapter(
    private val actionListener: LearningDetailsActionListener
) : RecyclerView.Adapter<LessonDetailsViewHolder>(), View.OnClickListener {

    var mLessonsDetailsList: List<Lesson> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonDetailsBinding.inflate(inflater, parent, false)

        binding.buttonStartOrRepeat.setOnClickListener(this)

        return LessonDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonDetailsViewHolder, position: Int) {
        val lessonDetails = mLessonsDetailsList[position]
        with(holder.binding) {
            textDescription.text = lessonDetails.description
            //textNameLesson.text = lessonDetails.name
            buttonStartOrRepeat.tag = lessonDetails
            if (lessonDetails.lessonLearned) {
                buttonStartOrRepeat.setBackgroundResource(R.drawable.button_lesson_repeat)
                buttonStartOrRepeat.text = "Повторить"
            }
        }
    }

    override fun getItemCount(): Int = mLessonsDetailsList.size

    class LessonDetailsViewHolder(val binding: ItemLessonDetailsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val lessonDetailId = view.tag as Lesson
        actionListener.onLearningLesson(lessonDetailId.id)
    }

}