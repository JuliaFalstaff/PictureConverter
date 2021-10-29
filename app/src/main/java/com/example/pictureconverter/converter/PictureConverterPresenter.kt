package com.example.pictureconverter.converter

import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class PictureConverterPresenter(private val converter: Converter, private val repository: IConvertRepository) :
        MvpPresenter<MainView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.initView()
    }

    fun selectImage(view: View) {
        viewState.pickPicture()
    }

    fun showContent(uri: Uri?) {
        viewState.showContent(uri)
    }

    fun convertPicture(uri: Uri?) {
        disposable.addAll(
                repository.convert(uri)
                        .subscribeOn(Schedulers.thread())
                        .observeOn(Schedulers.main())
                        .subscribe(
                                {
                                    viewState.showSuccess(it)
                                    viewState.showContent(it.toUri())
                                },
                                {
                                    viewState.showError(it)
                                })
        )
    }

    override fun onDestroy() {
        disposable.clear()
    }
}