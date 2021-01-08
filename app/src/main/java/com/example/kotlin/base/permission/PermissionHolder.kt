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