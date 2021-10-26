package com.example.pictureconverter.mvp.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pictureconverter.R
import com.example.pictureconverter.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

   lateinit var binding: ActivityMainBinding
//   val path: String = "C:\\Users\\Public\\Android\\PictureConverter\\app\\src\\main\\res\\drawable\\iceland.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.iceland)
        bitmap?.let {
            binding.imageViewToConvertPicture.setImageBitmap(it)
        }

        val pictureFile: File = File(applicationContext.filesDir, "iceland.png")
        val outputStream = FileOutputStream(pictureFile)

        binding.buttonConverterPicture.setOnClickListener {
            bitmap.let {
                it.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.close()
               binding.imageViewOfConvertedPicture.setImageBitmap(it)
            }
        }
    }
}