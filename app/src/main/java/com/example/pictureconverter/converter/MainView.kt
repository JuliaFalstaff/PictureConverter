package com.example.pictureconverter.converter

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface MainView : MvpView {

    fun showSuccess(path: String)

    fun showEmpty()

    fun pickImage()

    fun showError(throwable: Throwable)
}