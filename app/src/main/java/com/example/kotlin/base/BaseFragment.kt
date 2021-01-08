package com.example.kotlin.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin.base.permission.PermissionClosure
import com.example.kotlin.base.permission.PermissionHolder
import com.example.kotlin.base.permission.PermissionResult

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2020/12/31 09:59
 * <br>desc: TODO
 */
abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    private val permissionHolder: PermissionHolder = PermissionHolder()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun requestPermissions(vararg permission:String, callback: PermissionClosure){
        val requestCode = permissionHolder.generateRequestCode()
        permissionHolder.save(requestCode, callback)
        requestPermissions(permission, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHolder.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}