package com.easyprog.peil.fragments.repeat_word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyprog.peil.R
import com.easyprog.core.views.BaseFragment
import com.easyprog.core.views.BaseScreen
import com.easyprog.core.views.screenViewModel
import kotlinx.parcelize.Parcelize

class RepeatWordFragment : BaseFragment() {

    @Parcelize
    class Screen: BaseScreen

    override val viewModel by screenViewModel<RepeatWordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repeat_word, container, false)
    }
}