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

import android.util.SparseArray

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 15:14
 * <br>desc: 权限请求简单封装
 */
typealias PermissionClosure = PermissionResult.()->Unit

class PermissionHolder {

    private var requestCode: Int = MIN_REQUEST_CODE
    private val array: SparseArray<PermissionClosure> = SparseArray()

    fun generateRequestCode():Int{
        requestCode++
        if(requestCode > MAX_REQUEST_CODE) {
            requestCode =
                MIN_REQUEST_CODE
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
    }

    companion object {
        const val MAX_REQUEST_CODE = 2000
        const val MIN_REQUEST_CODE = 1
    }
}