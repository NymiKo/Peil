package com.easyprog.peil.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.easyprog.peil.R
import com.easyprog.core.views.BaseScreen
import kotlinx.parcelize.Parcelize

class ProfileFragment : Fragment() {

    @Parcelize
    class Screen: BaseScreen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}