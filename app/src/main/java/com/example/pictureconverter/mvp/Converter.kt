package com.example.pictureconverter.mvp

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class Converter : IConvertation {
    override fun bitmapToFile(bitmap: Bitmap?, fileToSave: File): Observable<Uri>  {
        return Observable.fromCallable {
            val outStream: OutputStream = FileOutputStream(fileToSave)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
            Uri.parse(fileToSave.absolutePath)
        }.subscribeOn(Schedulers.newThread())
    }
}