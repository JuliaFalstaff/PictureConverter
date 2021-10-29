package com.example.pictureconverter.converter

import com.example.pictureconverter.converter.model.PictureRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class PicurePresenter(private val converter: Converter, private val selectedPicture: PictureRepo) :
        MvpPresenter<MainView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.initListener()
    }

    fun selectedPicked(uri: String?) {

        disposable.addAll(
                selectedPicture.getPicture(uri)
                         .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    converter.convert(it)
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(
                                                    viewState::showImage,
                                                    viewState::showError
                                            )
                                },
                                viewState::showError
                        )
        )
    }


    override fun onDestroy() {
        disposable.dispose()
    }

}