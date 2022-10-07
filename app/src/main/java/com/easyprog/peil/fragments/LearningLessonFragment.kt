package com.easyprog.peil.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import com.easyprog.peil.R
import com.easyprog.peil.adapters.learnong_lesson_viewpager.LearningLessonActionListener
import com.easyprog.peil.adapters.learnong_lesson_viewpager.LearningLessonAdapter
import com.easyprog.peil.databinding.FragmentLearningLessonBinding
import com.easyprog.peil.fragments.dialog_exit_learning_lesson.DialogExitLearningLesson
import com.easyprog.peil.fragments.dialog_exit_learning_lesson.DialogListener
import kotlinx.parcelize.Parcelize

class LearningLessonFragment : BaseFragment() {

    @Parcelize
    class Screen(val idLessonDetail: Int) : BaseScreen

    private var _binding: FragmentLearningLessonBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: LearningLessonAdapter

    override val viewModel by screenViewModel<LearningLessonViewModel>()

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
                    viewModel.onBackPressed()
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
                viewModel.onBackPressed()
            }
        }).show(childFragmentManager, "DialogExit")
    }

    override fun onDestroyView() {
        binding.viewPagerLearningLesson.adapter = null
        _binding = null
        super.onDestroyView()
    }
}