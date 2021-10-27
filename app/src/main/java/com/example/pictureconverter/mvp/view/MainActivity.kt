package com.example.pictureconverter.mvp.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pictureconverter.R
import com.example.pictureconverter.databinding.ActivityMainBinding
import com.example.pictureconverter.mvp.Converter
import com.example.pictureconverter.mvp.IConvertation
import com.example.pictureconverter.mvp.model.PictureRepo
import com.example.pictureconverter.mvp.presenter.PicturePresenter
import io.reactivex.rxjava3.core.Observable
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class MainActivity : MvpAppCompatActivity(), IMainView  {

    lateinit var binding: ActivityMainBinding
    val presenter by moxyPresenter { PicturePresenter(PictureRepo(), Converter()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun init() {

        val bitmap = BitmapFactory.decodeResource(resources, presenter.setPictureToConvert())
        bitmap?.let {
            binding.imageViewToConvertPicture.setImageBitmap(it)
        }

        binding.buttonConverterPicture.setOnClickListener {
            if (bitmap != null) {
                val uri = bitmapToFile(bitmap)
                binding.imageViewOfConvertedPicture.setImageURI(uri)
            }
        }
    }

    fun createFileForCompressedPicture(): File {
       var fileToSave = applicationContext.getDir("Images", Context.MODE_PRIVATE)
        fileToSave.mkdirs()
        fileToSave = File(fileToSave, "new.png")
        return fileToSave
    }



    fun bitmapToFile(bitmap: Bitmap?) : Uri {
       return presenter.bitmapToFile(bitmap, createFileForCompressedPicture())
    }


//    override fun bitmapToFile(bitmap: Bitmap?, fileToSave: File): Uri {
//        fileToSave = applicationContext.getDir("Images", Context.MODE_PRIVATE)
//        fileToSave.mkdirs()
//        fileToSave = File(fileToSave, "new.png")
//        try {
//            val outStream: OutputStream = FileOutputStream(fileToSave)
//            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outStream)
//            outStream.flush()
//            outStream.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return Uri.parse(fileToSave.absolutePath)
//    }


}