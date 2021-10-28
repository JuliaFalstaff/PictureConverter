package com.example.pictureconverter.mvp.presenter


import android.net.Uri
import com.example.pictureconverter.mvp.ImageConverter
import com.example.pictureconverter.mvp.model.Image
import com.example.pictureconverter.mvp.scheduler.Schedulers
import com.example.pictureconverter.mvp.view.IMainView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import java.io.File

class ImagePresenter(private val converter: ImageConverter) : MvpPresenter<IMainView>() {

    val disposable = CompositeDisposable()
    private var sImage: Image? = null


    fun setChoosenImage(choosenFile: Uri) {
        viewState.setConvertedImage(choosenFile)
    }

    fun convert(toPath: File) {
        val image = sImage ?: return
        disposable.addAll(
                converter.convertBitmapToFile(image, toPath)
                        .subscribeOn(Schedulers.thread())
                        .observeOn(Schedulers.main())
                        .subscribe({ convertedImage ->
                            viewState.setConvertedImage(Uri.parse(convertedImage.absolutePath))
                        }, { error ->
                            viewState.showError(error)
                        })
        )
    }

    override fun onDestroy() {
        disposable.dispose()
    }
}