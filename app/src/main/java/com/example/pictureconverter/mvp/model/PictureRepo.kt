package com.example.pictureconverter.mvp.model

class PictureRepo {
    private val repositories = Picture()

    fun getPicture() : Picture = repositories
}