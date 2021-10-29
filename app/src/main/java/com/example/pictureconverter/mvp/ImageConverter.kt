//package com.example.pictureconverter.mvp
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import com.example.pictureconverter.mvp.model.Image
//import io.reactivex.rxjava3.core.Single
//import io.reactivex.rxjava3.schedulers.Schedulers
//import java.io.File
//import java.io.FileOutputStream
//import java.io.OutputStream
//
//class ImageConverter : IImageConvertation {
//
//    companion object {
//        private const val BITMAP_QUALITY = 100
//    }
//
//    override fun convertBitmapToFile(image: Image, pathToSaveFile: File): Single<File> {
//        return Single.create<File> { emitter ->
//            try {
//                val bitmap = BitmapFactory.decodeByteArray(image.data, 0, image.data.size)
//                val convertedImage = File(pathToSaveFile, "new-${image}.png")
//                val outStream: OutputStream = FileOutputStream(convertedImage)
//                bitmap.compress(Bitmap.CompressFormat.PNG, BITMAP_QUALITY, outStream)
//                if (!emitter.isDisposed) {
//                    emitter.onSuccess(convertedImage)
//                }
//            } catch (e: Throwable) {
//                if (!emitter.isDisposed) {
//                    emitter.onError(e)
//                }
//            }
//        }.subscribeOn(Schedulers.computation())
//    }
//}