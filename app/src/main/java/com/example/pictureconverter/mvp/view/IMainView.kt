package com.example.pictureconverter.mvp.view

import android.graphics.Bitmap
import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import java.io.File

@AddToEndSingle
interface IMainView : MvpView {
    fun setNonConvertedImage(oldImage: Uri?)
    fun setConvertedImage(newImage: Uri)
    fun showError(error: Throwable)
}