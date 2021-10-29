package com.example.pictureconverter.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.*

class Converter(private val context: Context) : IConvert {

    companion object {
        private const val fileName = "converted_image"
        private const val fileExt = "png"
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
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                        }
                    }
                    file.absolutePath
                }
    }
}
