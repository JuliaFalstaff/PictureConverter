package com.example.pictureconverter.converter

import android.net.Uri
import android.view.View
import com.example.pictureconverter.mvp.scheduler.Schedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class PictureConverterPresenter(private val converter: Converter, private val repository: IConvertRepository) :
        MvpPresenter<MainView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {

    }

    fun selectImage(view: View) {
        viewState.pickImage()
    }


    fun convertPicture(uri: Uri?) {
        disposable.addAll(
                repository.convert(uri)
                        .subscribeOn(Schedulers.thread())
                        .observeOn(Schedulers.main())
                        .subscribe(
                                {
                                    viewState.showSuccess(it)
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