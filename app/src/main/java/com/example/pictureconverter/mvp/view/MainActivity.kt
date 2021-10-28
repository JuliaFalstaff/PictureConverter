package com.example.pictureconverter.mvp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import com.example.pictureconverter.databinding.ActivityMainBinding
import com.example.pictureconverter.mvp.ImageConverter
import com.example.pictureconverter.mvp.presenter.ImagePresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView  {

    lateinit var binding: ActivityMainBinding
    val presenter by moxyPresenter { ImagePresenter(ImageConverter()) }
    var selectedFile: Uri? = null
    var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    private fun initView() {
        binding.buttonConverterPicture.setOnClickListener {
            try {
                val bitmap = BitmapFactory.decodeFile(path)
                path?.let { it1 -> presenter.convertationOfNewImage(bitmap, it1) }
            } catch (e : NullPointerException) {
                showError(e)
            }
        }

        binding.buttonToChoosePicture.setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"
            startActivityForResult(getIntent, 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.data?.let { presenter.setChoosenImage(it) }
        }
    }

    override fun setNonConvertedImage(oldImage: Uri?) {
        binding.imageViewToConvertPicture.setImageURI(oldImage)
    }

    override fun setConvertedImage(newImage: Uri) {
        binding.imageViewOfConvertedPicture.setImageURI(newImage)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}