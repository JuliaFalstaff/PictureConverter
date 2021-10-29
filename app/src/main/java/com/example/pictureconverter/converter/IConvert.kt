package com.example.pictureconverter.converter

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface IConvert {
    fun convert(uri: Uri?): Maybe<String>
}