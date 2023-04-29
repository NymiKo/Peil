package com.easyprog.peil.fragments.lessons_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.easyprog.core.ARG_SCREEN
import com.easyprog.peil.R
import com.easyprog.peil.activity.MainActivity
import com.easyprog.peil.adapters.LessonActionListener
import com.easyprog.peil.adapters.LessonsListAdapter
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import com.easyprog.peil.data.models.Lesson
import com.easyprog.peil.databinding.FragmentLessonsListBinding
import com.easyprog.peil.fragments.lesson.LessonFragment
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class LessonsListFragment : Fragment() {

    //binding
    private var _binding: FragmentLessonsListBinding? = null
    private val binding get() = _binding!!

    //adapter
    private lateinit var mAdapter: LessonsListAdapter
    private var mLayoutManager: LinearLayoutManager? = null

    //viewModel
    private val viewModel by viewModels<LessonsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLayoutManager = LinearLayoutManager(requireContext())
        setupAdapter()
        setupView()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        mAdapter = LessonsListAdapter(object : LessonActionListener {
            override fun onLessonDetails(lesson: Lesson, view: View) {
//                viewModel.onLessonPressed(lesson)
                viewModel.setExpandedLessons(mAdapter.expandedItems)
                val fragment = LessonFragment()
                fragment.arguments = Bundle().apply {
                    putParcelable("lesson", lesson)
                }
                (activity as MainActivity).supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addSharedElement(view, view.transitionName)
                    .replace(R.id.nav_host_fragment_container, fragment, LessonFragment().javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    private fun setupView() {
        binding.fabNextLesson.text = HtmlCompat.fromHtml(
            "Тебе сегодня надо повторить <b>слабые слова</b>",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerLessonList.layoutManager = mLayoutManager
        binding.recyclerLessonList.adapter = mAdapter.apply {
            viewModel.lessonsList.observe(viewLifecycleOwner) {
                mAdapter.mLessonsList = it as ArrayList
            }
            viewModel.expandedLessons.observe(viewLifecycleOwner) {
                mAdapter.expandedItems = it as ArrayList
            }
        }
        mAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
//        val itemAnimator = binding.recyclerLessonList.itemAnimator
//        if (itemAnimator is DefaultItemAnimator) {
//            itemAnimator.supportsChangeAnimations = false
//        }
    }

    override fun onDestroyView() {
        mLayoutManager = null
        binding.recyclerLessonList.adapter = null
        _binding = null
        super.onDestroyView()
    }
}