package com.easyprog.peil.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.easyprog.peil.R
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import com.easyprog.peil.databinding.FragmentBottomNavigationBinding
import com.easyprog.peil.fragments.community.CommunityFragment
import com.easyprog.peil.fragments.lessons_list.LessonsListFragment
import com.easyprog.peil.fragments.repeat_word.RepeatWordFragment
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.parcelize.Parcelize

class BottomNavigationFragment : BaseFragment() {

    @Parcelize
    class Screen : BaseScreen

    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding get() = _binding!!
    override val viewModel by screenViewModel<BottomNavigationViewModel>()
    private lateinit var mAdapterViewPager: BottomNavigationViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exitTransition = Hold().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        mAdapterViewPager = BottomNavigationViewPagerAdapter(this)
        setupViewPager()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.lessonsFragment -> {
                    binding.viewPagerBottomNavigation.currentItem = 0
                    true
                }
                R.id.repeatWordFragment -> {
                    binding.viewPagerBottomNavigation.currentItem = 1
                    true
                }
                R.id.communityFragment -> {
                    binding.viewPagerBottomNavigation.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.lessonsFragment -> {
                LessonsListFragment()
                true
            }
            R.id.repeatWordFragment -> {
                RepeatWordFragment()
                true
            }
            R.id.communityFragment -> {
                CommunityFragment()
                true
            }
            else -> false
        }
        //return super.onOptionsItemSelected(item)
    }

    private fun setupViewPager() {
        binding.viewPagerBottomNavigation.adapter = mAdapterViewPager
    }

    override fun onDestroyView() {
        //binding.viewPagerBottomNavigation.adapter = null
        _binding = null
        super.onDestroyView()
    }
}