package com.easyprog.peil.fragments.lesson

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import com.easyprog.peil.R
import com.easyprog.peil.adapters.viewpager.LearningDetailsActionListener
import com.easyprog.peil.adapters.viewpager.LessonDetailsAdapter
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.databinding.FragmentLessonBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

class LessonFragment : BaseFragment() {

    @Parcelize
    class Screen(val lesson: Lesson) : BaseScreen

    private lateinit var mAdapter: LessonDetailsAdapter

    //binding
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    override val viewModel by screenViewModel<LessonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel.lesson.observe(viewLifecycleOwner) { lesson ->
            binding.fragmentContainer.transitionName = "lesson_card_${lesson.id}"
            binding.nameLesson.text = lesson.name
            if (lesson.description.isBlank()) {
                binding.descriptionLesson.visibility = View.GONE
            }
            binding.descriptionLesson.text = lesson.description
            Picasso.get().load(lesson.imageUrl).resize(750, 500).into(binding.imageViewParallax)
        }
        binding.imageBackButton.setOnClickListener {
            viewModel.onBackPressed()
        }
        binding.viewPagerLessonDetails.adapter = mAdapter.apply {
            viewModel.lessonDetails.observe(viewLifecycleOwner) {
                mAdapter.mLessonsDetailsList = it
            }
        }

        binding.viewPagerLessonDetails.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            val viewPager = binding.viewPagerLessonDetails

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                val x =
                    ((viewPager.width * position + positionOffsetPixels) * computeFactor()).toInt()
                binding.horizontalScrollView.scrollTo(x, 0)
            }

            private fun computeFactor(): Float {

                return (binding.imageViewParallax.width - viewPager.width) /
                        (viewPager.width * (viewPager.adapter?.itemCount!!.minus(1))).toFloat()
            }
        })
    }

    private fun setupAdapter() {
        mAdapter = LessonDetailsAdapter(object : LearningDetailsActionListener {
            override fun onLearningLesson(lessonDetailsId: Int) {
                viewModel.onLearningLessonPressed(lessonDetailsId)
            }
        })
    }

    override fun onDestroyView() {
        binding.viewPagerLessonDetails.adapter = null
        _binding = null
        super.onDestroyView()
    }
}