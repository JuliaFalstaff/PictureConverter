package com.example.pictureconverter.mvp.presenter

import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import com.example.pictureconverter.mvp.ImageConverter
import com.example.pictureconverter.mvp.scheduler.Schedulers
import com.example.pictureconverter.mvp.view.IMainView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class ImagePresenter(private val converter: ImageConverter) : MvpPresenter<IMainView>() {

    val disposable = CompositeDisposable()


    fun setChoosenImage(choosenFile: Uri) {
        viewState.setConvertedImage(choosenFile)
    }

    fun convertationOfNewImage(image: Bitmap, pathToSaveFile: String) {
        disposable.addAll(
            converter.convertBitmapToFile(image, pathToSaveFile)
                    .subscribeOn(Schedulers.thread())
                    .observeOn(Schedulers.main())
                    .subscribe({ convertedImage ->
                        viewState.setConvertedImage(Uri.parse(convertedImage.absolutePath))
                    }, {error ->
                        viewState.showError(error)
                    })
        )
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}