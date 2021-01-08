/*
 * Copyright (c) 2021 Eshel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kotlin.home

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.base.launchMain
import com.example.kotlin.database.AppDatabase
import com.example.kotlin.database.entity.Event
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.text.StringBuilder

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/6 17:04
 * <br>desc: TODO
 */
class MessageFragment : BaseFragment(R.layout.fragment_message) {

    companion object {
        val TAG: String = "MessageFragment"
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.movementMethod = ScrollingMovementMethod.getInstance()
        Log.i(TAG, "onViewCreated: ")
        val sb: StringBuilder = StringBuilder()
        for (i in 1..5000) {
            sb.append(i)
        }
        for (i in 1..1000) {
            sb.append("H")
        }
        println(sb.toString())

        /*GlobalScope.launch(context = Dispatchers.Default) {
            //子线程查询数据库
            val data = AppDatabase.instance.userTrack().queryAll()
            //怎么切回主线程?
            delay()
            message.text = ""
            data.forEach {
                message.append("$it\n")
            }
        }*/
        launchMain {
            flow<List<Event>> {
                val events = AppDatabase.instance.userTrack().queryAll()
                emit(events)
            }.flowOn(context = Dispatchers.Default).collect {
                message.text = ""
                it.forEach {
                    message.append("$it\n")
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.i(TAG, "onHiddenChanged: $hidden")
    }
}