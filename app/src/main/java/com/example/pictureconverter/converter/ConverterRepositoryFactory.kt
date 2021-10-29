package com.example.pictureconverter.converter

import android.content.Context

object ConverterRepositoryFactory {
    fun create(context: Context) : IConvertRepository = Converter(context)
}