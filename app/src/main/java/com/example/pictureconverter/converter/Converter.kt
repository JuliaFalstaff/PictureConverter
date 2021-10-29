package com.example.pictureconverter.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import io.reactivex.rxjava3.core.Maybe
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File

class Converter(private val context: Context) : IConvertRepository {

    companion object {
        private const val fileName = "converted"
        private const val fileExt = "png"
        private const val BITMAP_QUALITY = 100
    }

    override fun convert(uri: Uri?): Maybe<String> {
        if (uri == null) {
            return Maybe.empty();
        }

        return Maybe
                .just(uri)
                .map {
                    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "$fileName.$fileExt")
                    file.createNewFile()

                    BufferedOutputStream(file.outputStream()).use { outStream ->
                        BufferedInputStream(context.contentResolver.openInputStream(it)).use { inputStream ->
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            bitmap.compress(Bitmap.CompressFormat.PNG, BITMAP_QUALITY, outStream)
                        }
                    }
                    file.absolutePath
                }
    }
}
