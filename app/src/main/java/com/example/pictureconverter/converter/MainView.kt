package com.example.pictureconverter.converter

import android.view.View
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface MainView : MvpView {
    fun showSuccess(path: String)
    fun showEmpty()
    fun pickImage()
    fun showError(throwable: Throwable)
    fun showPicture(view: View)
}