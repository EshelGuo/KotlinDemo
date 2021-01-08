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
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 15:14
 * <br>desc: 权限请求简单封装
 */
typealias PermissionClosure = PermissionResult.()->Unit

class PermissionHolder {

    private var requestCode: Int = MIN_REQUEST_CODE
    private val array: SparseArray<PermissionClosure> = SparseArray()

    @RequiresApi(Build.VERSION_CODES.R)
    fun requestPermissionForR(target: Any, permission: Array<out String>, callback: PermissionClosure? = null){
        if (Environment.isExternalStorageManager()) {
            callback?.invoke(PermissionResult(permission, IntArray(permission.size){ PackageManager.PERMISSION_GRANTED}))
        } else {
            var context: Context? = null
            if(target is Fragment){
                context = target.context
            }else if(target is Activity){
                context = target
            }

            context?.let {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:" + context?.packageName)
                intent.data = Uri.parse("package:" + context?.packageName)
                val code = generateRequestCode() + COUNT

                callback?.let {
                    save(code, it)
                }

                if(target is Fragment){
                    target.startActivityForResult(intent, code)
                }else if(target is Activity){
                    target.startActivityForResult(intent, code)
                }
            }
        }
    }

    fun generateRequestCode():Int{
        requestCode++
        if(requestCode > MAX_REQUEST_CODE) {
            requestCode = MIN_REQUEST_CODE
        }
        return requestCode
    }

    fun save(requestCode:Int, closure: PermissionClosure) {
        array.put(requestCode, closure)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
        val permissionResult = PermissionResult(permissions, grantResults)
        val closure = array.get(requestCode)
        closure?.invoke(permissionResult)
        array.remove(requestCode)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && requestCode > MAX_REQUEST_CODE && COUNT <= MAX_REQUEST_CODE + COUNT) {
            val closure = array.get(requestCode)
            closure?.let {
               val result:Int = if (Environment.isExternalStorageManager())
                   PackageManager.PERMISSION_GRANTED else PackageManager.PERMISSION_DENIED

                it.invoke(PermissionResult(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), intArrayOf(result, result)))
                array.remove(requestCode)
            }
        }
    }

    companion object {
        const val MAX_REQUEST_CODE = 40200
        const val MIN_REQUEST_CODE = 40001

        const val COUNT = MAX_REQUEST_CODE - MIN_REQUEST_CODE + 1
    }
}