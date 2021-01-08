package com.example.kotlin.base.permission

import android.content.pm.PackageManager

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 15:31
 * <br>desc: TODO
 */
class PermissionResult(val permissions: Array<out String>, val grantResults: IntArray) {

    val grantedPermissions: Array<out String>
    val deniedPermissions: Array<out String>

    init {
        val tempArr:Array<String> = Array(permissions.size) {""}
        var index = 0
        var lastIndex = tempArr.size - 1

        for (item in permissions.withIndex()) {
            if(grantResults[item.index] == PackageManager.PERMISSION_GRANTED){
                tempArr[index++] = item.value
            }else {
                tempArr[lastIndex--] = item.value
            }
        }

        grantedPermissions = Array(index) {tempArr[it]}
        deniedPermissions = Array(tempArr.size - index) {tempArr[it + index]}
    }

    /**
     * 监听授权成功的权限
     * permissions列表均为授权成功权限
     */
    fun granted(closure: (permissions: Array<out String>)->Unit){
        if(grantedPermissions.isNotEmpty()){
            closure.invoke(grantedPermissions)
        }
    }

    /**
     * 监听授权失败的权限
     * permissions列表均为授权失败权限
     */
    fun denied(closure: (permissions: Array<out String>)->Unit){
        if(deniedPermissions.isNotEmpty()) {
            closure.invoke(deniedPermissions)
        }
    }
}