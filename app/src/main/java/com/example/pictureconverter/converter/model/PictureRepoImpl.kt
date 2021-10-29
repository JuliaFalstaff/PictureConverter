package com.example.pictureconverter.converter.model

import android.content.Context
import android.provider.MediaStore
import androidx.core.net.toUri
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PictureRepoImpl(private val context: Context): PictureRepo {
    override fun getPicture(uri: String?): Single<String?> = Single.fromCallable {
        val filePath = arrayOf(MediaStore.Images.Media.DATA)
        val convertUri = uri?.toUri()
        val cursor =
                convertUri?.let { context.contentResolver.query(it, filePath, null, null, null) }
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePath[0])
        val resImage = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        resImage
    }.observeOn(Schedulers.computation()).delay(5, TimeUnit.SECONDS)
}