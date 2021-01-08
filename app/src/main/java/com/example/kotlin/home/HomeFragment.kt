package com.example.kotlin.home

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2020/12/31 10:08
 * <br>desc: TODO
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            granted {

            }
        }
    }
}