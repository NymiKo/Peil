package com.easyprog.peil.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.core.utils.loadImage
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.databinding.ItemLessonBinding

class LessonsListAdapter(
    private val actionListener: LessonActionListener
) : RecyclerView.Adapter<LessonsListAdapter.ViewHolder>(), View.OnClickListener {

    var mLessonsList: List<Lesson> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    var expandedItems: ArrayList<Lesson> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLessonBinding.inflate(inflater, parent, false)

        binding.cardExpandView.setOnClickListener(this)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lesson = mLessonsList[position]
        val isExpanded = expandedItems.contains(lesson)
        with(holder.binding) {
            cardExpandView.tag = lesson
            imageViewIconLesson.loadImage(lesson.iconUrl)
            textNumberLesson.text = "Урок ${lesson.number}"
            textNameLesson.text = lesson.name
            textDescriptionLesson.text = lesson.description
            textTimeLesson.text = lesson.time
            imageViewImageLesson.loadImage(lesson.imageUrl)

            cardExpandView.isVisible = isExpanded
            separator.isVisible = !isExpanded
            itemLessonContainer.setOnClickListener {
                if (expandedItems.contains(lesson)) {
                    expandedItems.remove(lesson)
                } else {
                    expandedItems.add(lesson)
                }
                notifyItemChanged(holder.bindingAdapterPosition)
            }
            if (!lesson.lessonLearned) {
                imageLessonLearned.visibility = View.GONE
            }
            cardExpandView.transitionName = "lesson_card_$position"
        }
    }

    override fun getItemCount(): Int = mLessonsList.size

    class ViewHolder(val binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val lesson = view.tag as Lesson
        actionListener.onLessonDetails(lesson, view)
    }
}