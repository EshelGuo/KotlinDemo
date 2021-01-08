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

import android.Manifest
import android.os.Bundle
import android.view.View
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.base.permission.requestPermissions

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2020/12/31 10:08
 * <br>desc: TODO
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE){
            onGranted {

            }
        }
    }
}