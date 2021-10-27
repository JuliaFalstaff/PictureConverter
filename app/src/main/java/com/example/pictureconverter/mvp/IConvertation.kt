package com.example.pictureconverter.mvp

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.io.File

interface IConvertation {
    fun bitmapToFile(bitmap: Bitmap?, fileToSave: File): Observable<Uri>
}