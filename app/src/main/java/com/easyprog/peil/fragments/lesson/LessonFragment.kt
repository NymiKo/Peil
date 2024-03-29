package com.easyprog.peil.fragments.lesson

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.easyprog.core.utils.loadImage
import com.easyprog.peil.R
import com.easyprog.peil.activity.MainActivity
import com.easyprog.peil.adapters.viewpager.LearningDetailsActionListener
import com.easyprog.peil.adapters.viewpager.LessonDetailsAdapter
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.databinding.FragmentLessonBinding
import com.easyprog.peil.fragments.learning_lesson.LearningLessonFragment
import com.google.android.material.transition.MaterialContainerTransform

class LessonFragment : Fragment() {

    private lateinit var mAdapter: LessonDetailsAdapter

    //binding
    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LessonViewModel>()
    private val lesson get() = requireArguments().getParcelable<Lesson>(ARG_LESSON)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData(lesson.id)
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
        binding.fragmentContainer.transitionName = "lesson_card_${lesson.id}"
        binding.toolbar.apply {
            title = getString(R.string.title_toolbar_lesson, lesson.number, lesson.name)
            subtitle = lesson.subtitle
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.imageViewParallax.loadImage(lesson.imageUrl)
        binding.viewPagerLessonDetails.adapter = mAdapter.apply {
            mAdapter.mLessonsDetailsList = listOf(lesson)
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
            override fun onLearningLesson(lessonDetailsId: String) {
                val fragment = LearningLessonFragment()
                fragment.arguments = Bundle().apply {
                    putString("lesson_id", lessonDetailsId)
                }
                (activity as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
                //findNavController().navigate(R.id.learningLessonFragment, bundleOf("lesson_id" to lessonDetailsId))
            }
        })
    }

    override fun onDestroyView() {
        binding.viewPagerLessonDetails.adapter = null
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_LESSON = "lesson"
        fun newInstance(lesson: Lesson): LessonFragment {
            return LessonFragment().apply {
                arguments = bundleOf(ARG_LESSON to lesson)
            }
        }
    }
}