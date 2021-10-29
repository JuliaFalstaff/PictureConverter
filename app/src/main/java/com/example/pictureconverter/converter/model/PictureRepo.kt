package com.example.pictureconverter.converter.model

import io.reactivex.rxjava3.core.Single

interface PictureRepo {
    fun getPicture(uri: String?) : Single<String?>
}