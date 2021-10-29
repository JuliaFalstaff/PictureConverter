package com.example.pictureconverter.converter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pictureconverter.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    lateinit var binding: ActivityMainBinding
    val presenter by moxyPresenter { PictureConverterPresenter(Converter(context = this), repository = ConverterRepositoryFactory.create(context = this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private var result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data: Uri? = it.data?.data
            presenter.convertPicture(data)
            presenter.showContent(data)
        }
    }

    override fun initView() {
        binding.buttonConverterPicture.setOnClickListener {
            presenter.selectImage(it)
         }
    }

    override fun showSuccess(path: String) {
        Toast.makeText(this, path, Toast.LENGTH_LONG).show()
    }

    override fun pickPicture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        result.launch(intent)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun showContent(uri: Uri?) {
        binding.imageViewOfConvertedPicture.setImageURI(uri)
    }
}