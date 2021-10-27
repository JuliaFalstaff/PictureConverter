package com.example.pictureconverter.mvp.presenter

import android.graphics.Bitmap
import android.net.Uri
import com.example.pictureconverter.mvp.IConvertation
import com.example.pictureconverter.mvp.model.PictureRepo
import com.example.pictureconverter.mvp.view.IMainView
import moxy.MvpPresenter
import java.io.File

class PicturePresenter(private val pictureRepo: PictureRepo, private val converter: IConvertation) :
    MvpPresenter<IMainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun setPictureToConvert(): Int {
        return pictureRepo.getPicture().pictureToConvert
    }

    fun bitmapToFile(bitmap: Bitmap?, fileToSave: File): Uri {
        converter.bitmapToFile(bitmap, fileToSave)
        return Uri.parse(fileToSave.absolutePath)
    }
}