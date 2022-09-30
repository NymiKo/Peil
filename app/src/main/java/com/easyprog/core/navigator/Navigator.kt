package com.easyprog.core.navigator

import com.easyprog.core.views.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen)

    fun goBack(result: Any? = null)

}