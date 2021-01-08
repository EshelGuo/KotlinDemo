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

package com.example.kotlin.base.permission

import android.Manifest
import android.os.Build
import androidx.core.app.ActivityCompat
import com.example.kotlin.base.BaseActivity
import com.example.kotlin.base.BaseFragment

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 17:40
 * <br>desc: TODO
 */

fun BaseFragment.requestPermissions(vararg permission:String, callback: PermissionClosure? = null){
    var containsRead:Boolean = false
    var containsWrite:Boolean = false
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
        containsWrite = permission.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        containsRead = permission.contains(Manifest.permission.READ_EXTERNAL_STORAGE)
        if(containsWrite || containsRead) permissionHolder.requestPermissionForR(this, permission, callback)
    }

    var offset: Int = 0
    val realPermissionList:Array<String> = Array(permission.size - containsRead.let { if(it) 1 else 0 } + containsWrite.let { if(it) 1 else 0 }) {
        if(containsRead && permission[it + offset] == Manifest.permission.READ_EXTERNAL_STORAGE) {
            offset+=1
        }

        if(containsWrite && permission[it + offset] == Manifest.permission.WRITE_EXTERNAL_STORAGE){
            offset+=1
        }

        return@Array permission[offset + it]
    }

    val requestCode = permissionHolder.generateRequestCode()
    callback?.let {
        permissionHolder.save(requestCode, it)
    }
    requestPermissions(realPermissionList, requestCode)
}

fun BaseActivity.requestPermissions(vararg permission:String, callback: PermissionClosure? = null){
    var containsRead:Boolean = false
    var containsWrite:Boolean = false
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
        containsWrite = permission.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        containsRead = permission.contains(Manifest.permission.READ_EXTERNAL_STORAGE)
        if(containsWrite || containsRead) permissionHolder.requestPermissionForR(this, permission, callback)
    }

    var offset: Int = 0
    val realPermissionList:Array<String> = Array(permission.size - containsRead.let { if(it) 1 else 0 } + containsWrite.let { if(it) 1 else 0 }) {
        if(containsRead && permission[it + offset] == Manifest.permission.READ_EXTERNAL_STORAGE) {
            offset+=1
        }

        if(containsWrite && permission[it + offset] == Manifest.permission.WRITE_EXTERNAL_STORAGE){
            offset+=1
        }

        return@Array permission[offset + it]
    }
    val requestCode = permissionHolder.generateRequestCode()
    callback?.let {
        permissionHolder.save(requestCode, it)
    }
    ActivityCompat.requestPermissions(this, realPermissionList, requestCode)
}