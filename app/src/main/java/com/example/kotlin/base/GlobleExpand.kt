package com.example.kotlin.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/7 09:47
 * <br>desc: TODO
 */

fun Any.launchMain(block: suspend CoroutineScope.() -> Unit){
    GlobalScope.launch(context = Dispatchers.Main, block = block)
}

fun Any.launchIO(block: suspend CoroutineScope.() -> Unit){
    GlobalScope.launch(context = Dispatchers.IO, block = block)
}

fun Any.launchDefault(block: suspend CoroutineScope.() -> Unit){
    GlobalScope.launch(context = Dispatchers.Default, block = block)
}