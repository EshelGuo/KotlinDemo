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

package com.example.kotlin.base

import android.content.Intent
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
    val permissionHolder: PermissionHolder = PermissionHolder()

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHolder.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permissionHolder.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}