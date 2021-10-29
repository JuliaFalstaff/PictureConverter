package com.example.pictureconverter.converter

import android.net.Uri
import io.reactivex.rxjava3.core.Maybe

interface IConvertRepository {
    fun convert(uri: Uri?): Maybe<String>
}