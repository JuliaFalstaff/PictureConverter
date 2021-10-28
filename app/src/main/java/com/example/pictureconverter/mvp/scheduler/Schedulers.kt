package com.example.pictureconverter.mvp.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object Schedulers {
    fun main(): Scheduler = AndroidSchedulers.mainThread()
    fun thread(): Scheduler = Schedulers.computation()
}