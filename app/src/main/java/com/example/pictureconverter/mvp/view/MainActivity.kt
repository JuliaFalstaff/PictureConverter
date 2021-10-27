package com.example.pictureconverter.mvp.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pictureconverter.R
import com.example.pictureconverter.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        //Этот код не работает
//        val bitmap = assetsToBitmap("iceland.jpg")

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.iceland)
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

    private fun bitmapToFile(bitmap: Bitmap?): Uri {
        var fileToSave = applicationContext.getDir("Images", Context.MODE_PRIVATE)
        fileToSave = File(fileToSave, "new.png")
        try {
            val outStream: OutputStream = FileOutputStream(fileToSave)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Uri.parse(fileToSave.absolutePath)
    }

    // Method to get a bitmap from assets
    private fun assetsToBitmap(fileName: String): Bitmap? {
        return try {
            val stream = assets.open(fileName)
            BitmapFactory.decodeStream(stream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


}