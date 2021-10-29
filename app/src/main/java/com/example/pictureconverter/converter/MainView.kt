package com.example.pictureconverter.converter


import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface MainView : MvpView {
    fun showSuccess(path: String)
    fun pickPicture()
    fun initView()
    fun showError(throwable: Throwable)
    fun showContent(uri: Uri?)
}