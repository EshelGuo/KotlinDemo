package com.example.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlin.base.permission.PermissionClosure
import com.example.kotlin.base.permission.PermissionHolder
import com.gyf.immersionbar.ImmersionBar

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2021/1/8 10:00
 * <br>desc: TODO
 */
open class BaseActivity : AppCompatActivity(){

    private val immersion: ImmersionConfig = ImmersionConfig()
    private val permissionHolder: PermissionHolder = PermissionHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onImmersion()
    }

    /**
     * 处理沉浸式状态栏
     */
    protected fun onImmersion(){
        immersion {  }
    }

    fun immersion(closure: ImmersionConfig.() -> Unit){
        closure.invoke(immersion)
        if(immersion.isOpen()){
            ImmersionBar.with(this)
                .statusBarDarkFont(immersion.isDark(), 0.2f)
                .init()
        }
    }

    fun requestPermissions(vararg permission:String, callback: PermissionClosure){
        val requestCode = permissionHolder.generateRequestCode()
        permissionHolder.save(requestCode, callback)
        ActivityCompat.requestPermissions(this, permission, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHolder.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}