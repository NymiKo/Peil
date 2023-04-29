package com.easyprog.peil.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import com.easyprog.peil.R
import com.easyprog.peil.adapters.learnong_lesson_viewpager.LearningLessonActionListener
import com.easyprog.peil.adapters.learnong_lesson_viewpager.LearningLessonAdapter
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.databinding.FragmentLearningLessonBinding
import com.easyprog.peil.fragments.dialog_exit_learning_lesson.DialogExitLearningLesson
import com.easyprog.peil.fragments.dialog_exit_learning_lesson.DialogListener
import com.easyprog.peil.fragments.lesson.LessonFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class LearningLessonFragment : Fragment() {

    private var _binding: FragmentLearningLessonBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: LearningLessonAdapter

    private val viewModel by viewModels<LearningLessonViewModel>()

    private val idLessonDetails get() = requireArguments().getInt(ARG_LESSON_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData(idLessonDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLearningLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewPager()
    }

    private fun setupView() {
        binding.imageCloseButton.setOnClickListener {
            dialogExit()
        }
    }

    private fun setupViewPager() {
        mAdapter = LearningLessonAdapter(object : LearningLessonActionListener {
            override fun nextStepLesson() {
                if (mAdapter.mLessonLearningSteps.lastIndex != binding.viewPagerLearningLesson.currentItem) {
                    binding.viewPagerLearningLesson.currentItem += 1
                } else {

                }
            }
        })
        binding.viewPagerLearningLesson.adapter = mAdapter.apply {
            viewModel.learningLessonStepsList.observe(viewLifecycleOwner) {
                mAdapter.mLessonLearningSteps = it
            }
        }
        binding.viewPagerLearningLesson.isUserInputEnabled = false
    }

    private fun dialogExit() {
        DialogExitLearningLesson(object : DialogListener{
            override fun onDialogNegativeClick(dialog: DialogFragment) {

            }
        }).show(childFragmentManager, "DialogExit")
    }

    override fun onDestroyView() {
        binding.viewPagerLearningLesson.adapter = null
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_LESSON_ID = "lesson_id"
        fun newInstance(idLessonDetails: Int): LearningLessonFragment {
            return LearningLessonFragment().apply {
                arguments = bundleOf(ARG_LESSON_ID to idLessonDetails)
            }
        }
    }
}