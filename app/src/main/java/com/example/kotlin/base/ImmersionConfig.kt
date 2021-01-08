package com.example.kotlin.base

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 10:04
 * <br>desc: 状态栏沉浸式配置
 */
class ImmersionConfig {

    private var open: Boolean = true
    private var dark: Boolean = false

    fun close(){
        open = false
    }

    fun open(){
        open = true
    }

    fun dark(){
        dark = true
    }

    fun highlight(){
        dark = false
    }

    fun isOpen():Boolean = open

    fun isDark():Boolean = dark
}