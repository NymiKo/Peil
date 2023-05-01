package com.easyprog.peil.adapters.learnong_lesson_viewpager

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.easyprog.peil.data.models.LessonLearningSteps
import com.easyprog.peil.databinding.ItemLearningLessonQuestionTranslateBinding
import com.easyprog.peil.databinding.ItemLearningLessonTheoryBinding

class LearningLessonAdapter(
    val listener: LearningLessonActionListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mLessonLearningSteps: List<LessonLearningSteps> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return mLessonLearningSteps[position].typeView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                val binding = ItemLearningLessonTheoryBinding.inflate(inflater, parent, false)
                LearningLessonTheoryViewHolder(binding, listener)
            }
            1 -> {
                val binding =
                    ItemLearningLessonQuestionTranslateBinding.inflate(inflater, parent, false)
                LearningLessonQuestionTranslateViewHolder(binding, listener)
            }
            else -> {
                val binding = ItemLearningLessonTheoryBinding.inflate(inflater, parent, false)
                LearningLessonTheoryViewHolder(binding, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (mLessonLearningSteps[position].typeView) {
            0 -> {
                (holder as LearningLessonTheoryViewHolder).bind(mLessonLearningSteps[position])
            }
            1 -> {
                (holder as LearningLessonQuestionTranslateViewHolder).bind(mLessonLearningSteps[position])
            }
        }
    }

    override fun getItemCount(): Int = mLessonLearningSteps.size

    class LearningLessonTheoryViewHolder(
        val binding: ItemLearningLessonTheoryBinding,
        val listener: LearningLessonActionListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LessonLearningSteps) {
            with(binding) {
                textPhrase.text = model.lessonPhrase
                textNewWord.text = model.newWord
                if (!model.practice) {
                    textNewWordTranslation.text = HtmlCompat.fromHtml(
                        model.description,
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                } else {
                    textNewWordTranslation.text = "Попытайся выполнить упражнение самостоятельно, а затем посмотри подсказку."
                    textExpandedHint.visibility = View.VISIBLE
                    textExpandedHint.text = model.description
                }
                buttonContinue.setOnClickListener { listener.nextStepLesson() }
            }
        }
    }

    class LearningLessonQuestionTranslateViewHolder(
        val binding: ItemLearningLessonQuestionTranslateBinding,
        val listener: LearningLessonActionListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LessonLearningSteps) {
            with(binding) {
                textQuestion.text = model.lessonPhrase
                textWordForTranslate.text = model.newWord
                textQuestionTranslate.text = model.description
                buttonContinue.setOnClickListener { listener.nextStepLesson() }
            }
        }
    }
}