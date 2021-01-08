package com.example.kotlin.base

import android.app.Application
import android.content.Context

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 18:25
 * <br>desc: TODO
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        instance = this
    }
}