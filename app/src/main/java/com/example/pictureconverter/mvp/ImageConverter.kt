package com.example.pictureconverter.mvp

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ImageConverter : IImageConvertation {

    override fun convertBitmapToFile(image: Bitmap?, pathToSaveFile: String): Single<File> {
        return Single.create<File> { emitter ->
            try {
                val convertedImage = File(pathToSaveFile, "new-${image}.png")
                val outStream: OutputStream = FileOutputStream(convertedImage)
                image?.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                if (!emitter.isDisposed) {
                    emitter.onSuccess(convertedImage)
                }
            } catch (e: Throwable) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }.subscribeOn(Schedulers.computation())
    }
}