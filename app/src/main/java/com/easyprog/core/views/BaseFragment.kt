package com.easyprog.core.views

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract val viewModel: BaseViewModel

}