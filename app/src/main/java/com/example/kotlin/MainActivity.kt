package com.example.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.kotlin.base.BaseActivity
import com.example.kotlin.base.launchDefault
import com.example.kotlin.database.AppDatabase
import com.example.kotlin.database.entity.Event
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener {
            findNavController(container.id).navigate(it.itemId)
            val name = getEventById(it.itemId)
            //将用户点击堆栈存入数据库, 并从数据库读取展示界面之上
            launchDefault {
                AppDatabase.instance.userTrack().insert(Event.Click(name))
            }
            true
        }
    }

    private fun getEventById(id: Int):String {
        return when(id){
            R.id.home -> "首页"
            R.id.category -> "分类"
            R.id.message -> "消息"
            R.id.mine -> "我的"
            else -> "其他"
        }
    }
}